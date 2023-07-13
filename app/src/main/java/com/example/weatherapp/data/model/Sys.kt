package com.example.weatherapp.data.model


data class Sys(
    val type: Int,
    val id: Long,
    val message: String,
    val country: String,
    val sunrise: String,
    val sunset: String
)