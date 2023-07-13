package com.example.weatherapp.data.util

import com.example.weatherapp.data.model.Clouds
import com.example.weatherapp.data.model.Rain
import com.example.weatherapp.data.model.Snow
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.model.Wind
import com.example.weatherapp.data.model.forecast.ForecastRain
import com.example.weatherapp.data.model.forecast.ForecastSnow
import com.example.weatherapp.data.model.forecast.ForecastWeatherResponse
import com.example.weatherapp.domain.entity.Forecast
import com.example.weatherapp.domain.entity.ForecastWeather
import com.example.weatherapp.domain.entity.Weather


fun convertWeatherResponseToWeather(weather: WeatherResponse): Weather =
    Weather(
        coordinate = weather.coordinate,
        weather = weather.weather,
        base = weather.base,
        weatherData = weather.weatherData,
        visibility = weather.visibility,
        wind = weather.wind ?: Wind(0.0, 0, 0.0),
        clouds = weather.clouds ?: Clouds(0),
        rain = weather.rain ?: Rain(0.0, 0.0),
        snow = weather.snow ?: Snow(0.0, 0.0),
        date = weather.date,
        sys = weather.sys,
        timezone = weather.timezone,
        id = weather.id ?: 0,
        name = weather.name ?: "Unknown",
        cod = weather.cod
    )


fun convertForecastWeatherResponseToForecast(forecast: ForecastWeatherResponse): Forecast =
    Forecast(
        cod = forecast.cod,
        message = forecast.message,
        cnt = forecast.cnt,
        list = convertForecastList(forecast.list),
        city = forecast.city
    )

fun convertForecastList(
    list: List<com.example.weatherapp.data.model.forecast.ForecastWeather>
): List<ForecastWeather> =
    list.map { forecastWeather ->
        convertForecastWeather(forecastWeather)
    }

fun convertForecastWeather(forecastWeather: com.example.weatherapp.data.model.forecast.ForecastWeather): ForecastWeather =
    ForecastWeather(
        date = forecastWeather.date,
        main = forecastWeather.main,
        weather = forecastWeather.weather,
        clouds = forecastWeather.clouds,
        wind = forecastWeather.wind,
        visibility = forecastWeather.visibility,
        probabilityOfPrecipitation = forecastWeather.probabilityOfPrecipitation,
        rain = forecastWeather.rain ?: ForecastRain(0.0),
        snow = forecastWeather.snow ?: ForecastSnow(0.0),
        sys = forecastWeather.sys,
        timeOfDataForecasted = forecastWeather.timeOfDataForecasted
    )