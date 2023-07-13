package com.example.weatherapp.data.model.forecast

data class ForecastWeatherResponse(
    val cod: Int,
    val message: String,
    val cnt: Int,
    val list: List<ForecastWeather>,
    val city: City
)