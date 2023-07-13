package com.example.weatherapp.data.model.forecast

import com.squareup.moshi.Json

data class ForecastSys(
    @Json(name = "pod") val partOfTheDay: String
)