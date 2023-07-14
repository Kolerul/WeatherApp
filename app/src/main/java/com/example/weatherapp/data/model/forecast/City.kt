package com.example.weatherapp.data.model.forecast

import com.example.weatherapp.data.model.Coordinate
import com.google.gson.annotations.SerializedName

data class City(
    val id: Long,
    val name: String,
    @SerializedName("coord") val coordinate: Coordinate,
    val country: String,
    val timezone: Int,
    val sunrise: String,
    val sunset: String
)