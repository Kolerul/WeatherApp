package com.example.weatherapp.data.network

import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.model.forecast.ForecastWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): WeatherResponse

    @GET("forecast")
    suspend fun getFiveDaysForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): ForecastWeatherResponse


    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}