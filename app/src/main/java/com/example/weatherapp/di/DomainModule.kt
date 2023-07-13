package com.example.weatherapp.di

import com.example.weatherapp.data.repository.CurrentWeatherRepositoryImplementation
import com.example.weatherapp.data.repository.ForecastWeatherRepositoryImplementation
import com.example.weatherapp.domain.repository.CurrentWeatherRepository
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun provideWeatherRepository(repository: CurrentWeatherRepositoryImplementation): CurrentWeatherRepository

    @Binds
    fun provideForecastRepository(repository: ForecastWeatherRepositoryImplementation): ForecastWeatherRepository
}