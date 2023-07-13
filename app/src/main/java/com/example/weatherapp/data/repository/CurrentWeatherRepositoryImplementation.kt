package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.util.convertWeatherResponseToWeather
import com.example.weatherapp.domain.repository.CurrentWeatherRepository
import com.example.weatherapp.domain.entity.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherRepositoryImplementation @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatcher: CoroutineDispatcher
) : CurrentWeatherRepository {

    override suspend fun getWeather(): Weather = withContext(dispatcher) {
        val response = weatherApi.getCurrentWeather(CITY, API_KEY)
        Log.d("Repository", response.toString())
        convertWeatherResponseToWeather(response)
    }

    companion object {
        const val CITY = "Saint Petersburg"
        const val API_KEY = "d9e6fe2ca9bd114df14262b014663852"
    }
}