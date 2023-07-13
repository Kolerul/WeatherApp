package com.example.weatherapp.data.model.forecast

import com.squareup.moshi.Json

data class ForecastRain(
    @Json(name = "3h") val rainVolumeForThreeHours: Double?
)