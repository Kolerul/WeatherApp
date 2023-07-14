package com.example.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastWeatherData(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val temperatureFeelsLike: Double,
    @SerializedName("temp_min") val minTemperature: Double,
    @SerializedName("temp_max") val maxTemperature: Double,
    val pressure: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("grnd_level") val groundLevel: Int,
    val humidity: Int,
    @SerializedName("temp_kf") val temperatureKf: Double
)