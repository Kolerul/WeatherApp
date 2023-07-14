package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName


data class Wind(
    val speed: Double,
    @SerializedName("deg") val degrees: Int,
    val gust: Double
)