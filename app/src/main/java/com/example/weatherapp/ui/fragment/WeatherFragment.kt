package com.example.weatherapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.domain.entity.Weather
import com.example.weatherapp.presentation.uistate.WeatherUIState
import com.example.weatherapp.presentation.viewmodel.CurrentWeatherViewModel

class WeatherFragment : BaseFragment<FragmentWeatherBinding>(FragmentWeatherBinding::inflate) {

    private val viewModel: CurrentWeatherViewModel by activityViewModels {
        (requireActivity().application as WeatherApp).appComponent.viewModelsFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUiObserver()
        setOnClickListeners()
    }

    private fun setUiObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is WeatherUIState.Initialization -> viewModel.getWeather()
                is WeatherUIState.Loading -> {}
                is WeatherUIState.Success -> setData(state.weather)
                is WeatherUIState.Error -> {}
            }
        }
    }

    private fun setOnClickListeners() {
        notNullBinding.apply {
            detailButton.setOnClickListener {
                findNavController().navigate(R.id.action_weatherFragment_to_detailedFragment)
            }

            forecastButton.setOnClickListener {
                findNavController().navigate(R.id.action_weatherFragment_to_forecastFragment)
            }
        }
    }

    private fun setData(weather: Weather) {
        notNullBinding.apply {
            info.text = weather.name
            degrees.text = requireContext().getString(
                R.string.degrees,
                (weather.weatherData.temperature - 273.15).toInt().toString()
            )
            windSpeed.text =
                requireContext().getString(R.string.wind_speed, weather.wind.speed.toString())
            clouds.text =
                requireContext().getString(R.string.clouds, weather.clouds.cloudsPercent.toString())
            humidity.text = requireContext().getString(
                R.string.humidity,
                weather.weatherData.humidity.toString()
            )

            Log.d("WeatherFragment", weather.weather[0].toString())

            when (weather.weather[0].main) {
                "Clear" -> {
                    if (weather.date > weather.sys.sunrise && weather.date < weather.sys.sunset)
                        weatherImage.setImageResource(R.drawable.ic_sunny)
                    else weatherImage.setImageResource(R.drawable.ic_moon)
                }

                "Clouds" -> weatherImage.setImageResource(R.drawable.ic_cloud)
                "Rain" -> weatherImage.setImageResource(R.drawable.ic_rain)
                "Snow" -> weatherImage.setImageResource(R.drawable.ic_snow)
                "Extreme" -> weatherImage.setImageResource(R.drawable.ic_flashlight)
            }
        }
    }
}