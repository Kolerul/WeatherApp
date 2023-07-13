package com.example.weatherapp.presentation.uistate

import com.example.weatherapp.domain.entity.Weather

sealed class WeatherUIState {
    object Initialization : WeatherUIState()
    object Loading : WeatherUIState()
    data class Success(val weather: Weather) : WeatherUIState()
    data class Error(val errorCode: Int) : WeatherUIState()
}