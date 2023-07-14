package com.example.weatherapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.databinding.FragmentDetailedBinding
import com.example.weatherapp.domain.entity.Weather
import com.example.weatherapp.presentation.uistate.WeatherUIState
import com.example.weatherapp.presentation.viewmodel.CurrentWeatherViewModel

class DetailedFragment : BaseFragment<FragmentDetailedBinding>(FragmentDetailedBinding::inflate) {

    private val viewModel: CurrentWeatherViewModel by activityViewModels {
        (requireActivity().application as WeatherApp).appComponent.viewModelsFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUiObserver()
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

    private fun setData(weather: Weather) {
        notNullBinding.apply {
            info.text = weather.name
            degrees.text = requireContext().getString(
                R.string.degrees,
                (weather.weatherData.temperature - 273.15).toInt().toString()
            )
            feelsLike.text = requireContext().getString(
                R.string.feels_like,
                (weather.weatherData.temperatureFeelsLike - 273.15).toInt().toString()
            )
            windSpeed.text =
                requireContext().getString(R.string.wind_speed, weather.wind.speed.toString())
            windDegrees.text =
                requireContext().getString(R.string.wind_speed, weather.wind.degrees.toString())
            windGust.text =
                requireContext().getString(R.string.wind_speed, weather.wind.gust.toString())
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
        }
    }
}