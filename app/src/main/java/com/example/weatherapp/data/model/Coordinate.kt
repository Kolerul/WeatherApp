package com.example.weatherapp.data.model

import com.squareup.moshi.Json

data class Coordinate(
    @Json(name = "lat") val latitude: Double,
    @Json(name = "lon") val longitude: Double
)
