package com.example.weatherapp.data.repository

import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.util.convertForecastWeatherResponseToForecast
import com.example.weatherapp.domain.entity.Forecast
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastWeatherRepositoryImplementation @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatcher: CoroutineDispatcher
) : ForecastWeatherRepository {

    override suspend fun getForecastForFiveDays(): Forecast = withContext(dispatcher) {
        val response =
            weatherApi.getFiveDaysForecast(
                CurrentWeatherRepositoryImplementation.CITY,
                CurrentWeatherRepositoryImplementation.API_KEY
            )
        convertForecastWeatherResponseToForecast(response)
    }

}