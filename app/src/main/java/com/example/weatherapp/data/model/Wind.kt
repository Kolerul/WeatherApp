package com.example.weatherapp.data.model

import com.squareup.moshi.Json

data class Wind(
    val speed: Double,
    @Json(name = "deg") val degrees: Int,
    val gust: Double
)