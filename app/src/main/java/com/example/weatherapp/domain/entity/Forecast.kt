package com.example.weatherapp.domain.entity

import com.example.weatherapp.data.model.forecast.City

data class Forecast(
    val cod: Int,
    val message: String,
    val cnt: Int,
    val list: List<ForecastWeather>,
    val city: City
)