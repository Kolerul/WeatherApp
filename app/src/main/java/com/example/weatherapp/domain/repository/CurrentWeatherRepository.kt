package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.entity.Weather


interface CurrentWeatherRepository {

    suspend fun getWeather(): Weather

}