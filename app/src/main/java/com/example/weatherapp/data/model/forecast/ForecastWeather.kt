package com.example.weatherapp.data.model.forecast

import com.example.weatherapp.data.model.Clouds
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.Wind
import com.squareup.moshi.Json

data class ForecastWeather(
    @Json(name = "dt") val date: String,
    val main: ForecastWeatherData,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    @Json(name = "pop") val probabilityOfPrecipitation: Int,
    val rain: ForecastRain?,
    val snow: ForecastSnow?,
    val sys: ForecastSys,
    @Json(name = "dt_txt") val timeOfDataForecasted: String
)