package com.example.weatherapp.domain.entity

import com.example.weatherapp.data.model.Clouds
import com.example.weatherapp.data.model.Coordinate
import com.example.weatherapp.data.model.Rain
import com.example.weatherapp.data.model.Snow
import com.example.weatherapp.data.model.Sys
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherData
import com.example.weatherapp.data.model.Wind

data class Weather(
    val coordinate: Coordinate,
    val weather: List<Weather>,
    val base: String,
    val weatherData: WeatherData,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val rain: Rain,
    val snow: Snow,
    val date: String,
    val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)