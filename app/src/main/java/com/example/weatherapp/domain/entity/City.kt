package com.example.weatherapp.domain.entity

import com.example.weatherapp.data.model.Coordinate


data class City(
    val id: Long,
    val name: String,
    val coordinate: Coordinate,
    val country: String,
    val timezone: Int,
    val sunrise: String,
    val sunset: String
)