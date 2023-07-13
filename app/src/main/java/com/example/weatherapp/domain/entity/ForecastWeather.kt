package com.example.weatherapp.domain.entity

import com.example.weatherapp.data.model.Clouds
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.Wind
import com.example.weatherapp.data.model.forecast.ForecastRain
import com.example.weatherapp.data.model.forecast.ForecastSnow
import com.example.weatherapp.data.model.forecast.ForecastSys
import com.example.weatherapp.data.model.forecast.ForecastWeatherData

data class ForecastWeather(
    val date: String,
    val main: ForecastWeatherData,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val probabilityOfPrecipitation: Int,
    val rain: ForecastRain,
    val snow: ForecastSnow,
    val sys: ForecastSys,
    val timeOfDataForecasted: String
)