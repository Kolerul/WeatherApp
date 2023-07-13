package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.entity.Forecast

interface ForecastWeatherRepository {

    suspend fun getForecastForFiveDays(): Forecast
}