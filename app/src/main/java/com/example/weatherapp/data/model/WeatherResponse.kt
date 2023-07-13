package com.example.weatherapp.data.model

import com.squareup.moshi.Json

data class WeatherResponse(
    val coordinate: Coordinate,
    val weather: List<Weather>,
    val base: String,
    @Json(name = "main") val weatherData: WeatherData,
    val visibility: Int,
    val wind: Wind?,
    val clouds: Clouds?,
    val rain: Rain?,
    val snow: Snow?,
    @Json(name = "dt") val date: String,
    val sys: Sys,
    val timezone: Int,
    val id: Long?,
    val name: String?,
    val cod: Int
)