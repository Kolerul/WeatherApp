package com.example.weatherapp.data.model.forecast

import com.example.weatherapp.data.model.Clouds
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.Wind
import com.google.gson.annotations.SerializedName

data class ForecastWeather(
    @SerializedName("dt") val date: String,
    val main: ForecastWeatherData,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    @SerializedName("pop") val probabilityOfPrecipitation: Double,
    val rain: ForecastRain?,
    val snow: ForecastSnow?,
    val sys: ForecastSys,
    @SerializedName("dt_txt") val timeOfDataForecasted: String
)