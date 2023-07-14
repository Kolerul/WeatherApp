package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("coord") val coordinate: Coordinate,
    val weather: List<Weather>,
    val base: String,
    @SerializedName("main") val weatherData: WeatherData,
    val visibility: Int,
    val wind: Wind?,
    val clouds: Clouds?,
    val rain: Rain?,
    val snow: Snow?,
    @SerializedName("dt") val date: String,
    val sys: Sys,
    val timezone: Int,
    val id: Long?,
    val name: String?,
    val cod: Int
)