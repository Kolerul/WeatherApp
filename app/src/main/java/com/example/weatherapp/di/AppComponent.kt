package com.example.weatherapp.di

import com.example.weatherapp.presentation.ViewModelFactory
import com.example.weatherapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [NetworkModule::class,
        CoroutineModule::class,
        DomainModule::class,
        PresentationModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun inject(activity: MainActivity)

    fun viewModelsFactory(): ViewModelFactory
}