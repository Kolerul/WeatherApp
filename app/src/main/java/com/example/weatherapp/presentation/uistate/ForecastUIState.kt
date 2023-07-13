package com.example.weatherapp.presentation.uistate

import com.example.weatherapp.domain.entity.Forecast

sealed class ForecastUIState {
    object Initialization : ForecastUIState()
    object Loading : ForecastUIState()
    data class Success(val forecast: Forecast) : ForecastUIState()
    data class Error(val errorCode: Int) : ForecastUIState()
}