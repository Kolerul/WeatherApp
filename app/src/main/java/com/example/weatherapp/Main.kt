package com.example.weatherapp

import com.example.weatherapp.data.network.WeatherApi
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun main() = runBlocking {

    val factory = GsonConverterFactory.create(GsonBuilder().setLenient().create())

    val logger = HttpLoggingInterceptor()
    logger.setLevel(HttpLoggingInterceptor.Level.BASIC)

    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(WeatherApi.BASE_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()

    val api = retrofit.create(WeatherApi::class.java)

    //30.19 59.57 d9e6fe2ca9bd114df14262b014663852
    val response =
        api.getCurrentWeather("Saint Petersburg", "d9e6fe2ca9bd114df14262b014663852")
    println(response.toString())
}

