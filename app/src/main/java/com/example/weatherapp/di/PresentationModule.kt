package com.example.weatherapp.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.presentation.viewmodel.CurrentWeatherViewModel
import com.example.weatherapp.presentation.viewmodel.ForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    fun bindCurrentWeatherViewModel(viewModel: CurrentWeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    fun bindForecastViewModel(viewModel: ForecastViewModel): ViewModel
}