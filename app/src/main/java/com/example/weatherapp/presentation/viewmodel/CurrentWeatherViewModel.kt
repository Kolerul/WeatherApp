package com.example.weatherapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.CurrentWeatherRepository
import com.example.weatherapp.presentation.uistate.WeatherUIState
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val repository: CurrentWeatherRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<WeatherUIState>(WeatherUIState.Initialization)
    val uiState: LiveData<WeatherUIState>
        get() = _uiState

    fun getWeather() {
        _uiState.value = WeatherUIState.Loading
        viewModelScope.launch {
            try {
                val weather = repository.getWeather()
                Log.d("ViewModel", weather.toString())
                _uiState.value = WeatherUIState.Success(weather)
            } catch (e: Exception) {
                _uiState.value = WeatherUIState.Error(1)
                Log.d("ViewModel", "${e.message}")
            }
        }
    }
}