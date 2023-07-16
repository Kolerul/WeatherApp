package com.example.weatherapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.databinding.FragmentForecastBinding
import com.example.weatherapp.domain.entity.ForecastWeather
import com.example.weatherapp.presentation.adapter.ForecastWeatherAdapter
import com.example.weatherapp.presentation.uistate.ForecastUIState
import com.example.weatherapp.presentation.viewmodel.ForecastViewModel
import com.google.android.material.snackbar.Snackbar

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
                is ForecastUIState.Loading -> showProgressBar()
                is ForecastUIState.Success -> showData(state.forecast.list)
                is ForecastUIState.Error -> showError(state.errorCode)
            }
        }
    }

    private fun setOnClickListener() {
        notNullBinding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.refresh_button -> {
                        viewModel.getForecast()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun showProgressBar() {
        notNullBinding.apply {
            recyclerView.visibility = View.GONE
            loadingProgressBar.visibility = View.VISIBLE
            toolbar.menu.findItem(R.id.refresh_button).isEnabled = false
        }
    }

    private fun showData(list: List<ForecastWeather>) {
        notNullBinding.apply {
            recyclerView.visibility = View.VISIBLE
            loadingProgressBar.visibility = View.GONE
            toolbar.menu.findItem(R.id.refresh_button).isEnabled = true
        }

        val adapter = ForecastWeatherAdapter()
        notNullBinding.apply {
            recyclerView.adapter = adapter
        }
        adapter.submitList(list)
    }

    private fun showError(errorCode: Int) {
        notNullBinding.apply {
            recyclerView.visibility = View.VISIBLE
            loadingProgressBar.visibility = View.GONE
            toolbar.menu.findItem(R.id.refresh_button).isEnabled = true
        }

        showSnackBar(
            requireActivity().getString(errorCode)
        )
    }


    private fun showSnackBar(text: String) {
        Snackbar.make(notNullBinding.recyclerView, text, Snackbar.LENGTH_SHORT).show()
    }
}