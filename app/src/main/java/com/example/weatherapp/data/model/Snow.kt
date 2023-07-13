package com.example.weatherapp.data.model

import com.squareup.moshi.Json

data class Snow(
    @Json(name = "1h") val snowVolumeForOneHour: Double?,
    @Json(name = "3h") val snowVolumeForThreeHours: Double?
)