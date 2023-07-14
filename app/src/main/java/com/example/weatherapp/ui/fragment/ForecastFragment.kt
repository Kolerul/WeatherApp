package com.example.weatherapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.databinding.FragmentForecastBinding
import com.example.weatherapp.domain.entity.ForecastWeather
import com.example.weatherapp.presentation.adapter.ForecastWeatherAdapter
import com.example.weatherapp.presentation.uistate.ForecastUIState
import com.example.weatherapp.presentation.viewmodel.ForecastViewModel

class ForecastFragment : BaseFragment<FragmentForecastBinding>(FragmentForecastBinding::inflate) {

    private val viewModel by viewModels<ForecastViewModel> {
        (requireActivity().application as WeatherApp).appComponent.viewModelsFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUiObserver()
        setOnClickListener()
    }

    private fun setUiObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ForecastUIState.Initialization -> viewModel.getForecast()
                is ForecastUIState.Loading -> {}
                is ForecastUIState.Success -> setAdapter(state.forecast.list)
                is ForecastUIState.Error -> {}
            }
        }
    }

    private fun setOnClickListener() {
        notNullBinding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setAdapter(list: List<ForecastWeather>) {
        Log.d("ForecastFragment", list.size.toString())
        val adapter = ForecastWeatherAdapter()
        notNullBinding.apply {
            recyclerView.adapter = adapter
        }
        adapter.submitList(list)
    }
}