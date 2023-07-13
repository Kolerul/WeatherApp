package com.example.weatherapp.data.model.forecast

import com.squareup.moshi.Json

data class ForecastSnow(
    @Json(name = "3h") val snowVolumeForThreeHours: Double?
)