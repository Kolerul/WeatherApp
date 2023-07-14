package com.example.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastSys(
    @SerializedName("pod") val partOfTheDay: String
)