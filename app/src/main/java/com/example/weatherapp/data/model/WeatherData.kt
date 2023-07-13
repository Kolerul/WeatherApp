package com.example.weatherapp.data.model

import com.squareup.moshi.Json


data class WeatherData(
    @Json(name = "temp") val temperature: Double,
    @Json(name = "feels_like") val temperatureFeelsLike: Double,
    @Json(name = "temp_min") val minTemperature: Double,
    @Json(name = "temp_max") val maxTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    @Json(name = "sea_level") val seaLevel: Int,
    @Json(name = "grnd_level") val groundLevel: Int
)