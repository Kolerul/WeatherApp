package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName


data class WeatherData(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val temperatureFeelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("temp_min") val minTemperature: Double,
    @SerializedName("temp_max") val maxTemperature: Double,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("grnd_level") val groundLevel: Int
)