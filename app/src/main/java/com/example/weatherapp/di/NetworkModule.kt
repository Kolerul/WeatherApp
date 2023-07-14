package com.example.weatherapp.di

import com.example.weatherapp.data.network.WeatherApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Provides
    fun provideFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)


    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

    @Provides
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)

}