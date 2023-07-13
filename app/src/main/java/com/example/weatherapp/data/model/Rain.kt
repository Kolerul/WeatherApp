package com.example.weatherapp.data.model

import com.squareup.moshi.Json

data class Rain(
    @Json(name = "1h") val rainVolumeForOneHour: Double?,
    @Json(name = "3h") val rainVolumeForThreeHours: Double?
)