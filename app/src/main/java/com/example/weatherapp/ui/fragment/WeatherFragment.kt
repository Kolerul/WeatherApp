package com.example.weatherapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.uistate.ForecastUIState
import com.example.weatherapp.presentation.uistate.WeatherUIState
import com.example.weatherapp.presentation.viewmodel.CurrentWeatherViewModel

class WeatherFragment : BaseFragment<FragmentWeatherBinding>(FragmentWeatherBinding::inflate) {

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
                is WeatherUIState.Success -> notNullBinding.info.text = state.weather.toString()
                is WeatherUIState.Error -> {}
            }
        }
    }
}