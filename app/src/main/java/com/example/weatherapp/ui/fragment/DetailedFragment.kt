package com.example.weatherapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.databinding.FragmentDetailedBinding
import com.example.weatherapp.domain.entity.Weather
import com.example.weatherapp.presentation.uistate.WeatherUIState
import com.example.weatherapp.presentation.viewmodel.CurrentWeatherViewModel
import com.google.android.material.snackbar.Snackbar

class DetailedFragment : BaseFragment<FragmentDetailedBinding>(FragmentDetailedBinding::inflate) {

    private val viewModel: CurrentWeatherViewModel by activityViewModels {
        (requireActivity().application as WeatherApp).appComponent.viewModelsFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUiObserver()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        notNullBinding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.refresh_button -> {
                        viewModel.getWeather()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun setUiObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is WeatherUIState.Initialization -> viewModel.getWeather()
                is WeatherUIState.Loading -> showProgressBar()
                is WeatherUIState.Success -> showData(state.weather)
                is WeatherUIState.Error -> showError(state.errorCode)
            }
        }
    }

    private fun showProgressBar() {
        notNullBinding.apply {
            contentLayout.visibility = View.GONE
            loadingProgressBar.visibility = View.VISIBLE
            toolbar.menu.findItem(R.id.refresh_button).isEnabled = false
        }
    }

    private fun showData(weather: Weather) {
        notNullBinding.apply {
            contentLayout.visibility = View.VISIBLE
            loadingProgressBar.visibility = View.GONE
            toolbar.menu.findItem(R.id.refresh_button).isEnabled = true

            info.text = weather.name
            degrees.text = requireContext().getString(
                R.string.degrees,
                (weather.weatherData.temperature - 273.15).toInt().toString()
            )
            feelsLike.text = requireContext().getString(
                R.string.feels_like,
                (weather.weatherData.temperatureFeelsLike - 273.15).toInt().toString()
            )
            pressure.text = requireContext().getString(
                R.string.pressure,
                weather.weatherData.pressure.toString()
            )

            windSpeed.text =
                requireContext().getString(R.string.wind_speed, weather.wind.speed.toString())
            windDegrees.text =
                requireContext().getString(R.string.wind_degrees, weather.wind.degrees.toString())
            windGust.text =
                requireContext().getString(R.string.wind_gust, weather.wind.gust.toString())
            clouds.text =
                requireContext().getString(R.string.clouds, weather.clouds.cloudsPercent.toString())
            rain.text = requireContext().getString(
                R.string.rain,
                weather.rain.rainVolumeForOneHour.toString()
            )
            snow.text = requireContext().getString(
                R.string.snow,
                weather.snow.snowVolumeForOneHour.toString()
            )
            humidity.text = requireContext().getString(
                R.string.humidity,
                weather.weatherData.humidity.toString()
            )
            visibility.text =
                requireContext().getString(R.string.visibility, weather.visibility.toString())
            weatherDescription.text = weather.weather[0].description

            when (weather.weather[0].main) {
                "Clear" -> {
                    if (weather.date > weather.sys.sunrise && weather.date < weather.sys.sunset) {
                        weatherImage.setImageResource(R.drawable.ic_sunny)
                        contentLayout.setBackgroundResource(R.drawable.sunny)
                    } else {
                        weatherImage.setImageResource(R.drawable.ic_moon)
                        contentLayout.setBackgroundResource(R.drawable.night)
                    }
                }

                "Clouds" -> {
                    weatherImage.setImageResource(R.drawable.ic_cloud)
                    contentLayout.setBackgroundResource(R.drawable.clouds)
                }

                "Rain" -> {
                    weatherImage.setImageResource(R.drawable.ic_rain)
                    contentLayout.setBackgroundResource(R.drawable.rain)
                }

                "Snow" -> {
                    weatherImage.setImageResource(R.drawable.ic_snow)
                    contentLayout.setBackgroundResource(R.drawable.snow)
                }

                "Extreme" -> {
                    weatherImage.setImageResource(R.drawable.ic_lightning)
                    contentLayout.setBackgroundResource(R.drawable.extreme)
                }
            }
        }
    }

    private fun showError(errorCode: Int) {
        notNullBinding.apply {
            contentLayout.visibility = View.VISIBLE
            loadingProgressBar.visibility = View.GONE
            toolbar.menu.findItem(R.id.refresh_button).isEnabled = true
        }

        showSnackBar(
            requireActivity().getString(errorCode)
        )
    }


    private fun showSnackBar(text: String) {
        Snackbar.make(notNullBinding.contentLayout, text, Snackbar.LENGTH_SHORT).show()
    }
}