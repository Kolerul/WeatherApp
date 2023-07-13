package com.example.weatherapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import com.example.weatherapp.presentation.uistate.ForecastUIState
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val repository: ForecastWeatherRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<ForecastUIState>(ForecastUIState.Initialization)
    val uiState: LiveData<ForecastUIState>
        get() = _uiState

    fun getForecast() {
        _uiState.value = ForecastUIState.Loading
        viewModelScope.launch {
            try {
                val forecast = repository.getForecastForFiveDays()
                _uiState.value = ForecastUIState.Success(forecast)
            } catch (e: Exception) {
                _uiState.value = ForecastUIState.Error(1)
                Log.d("ViewModel", "${e.message}")
            }
        }
    }
}