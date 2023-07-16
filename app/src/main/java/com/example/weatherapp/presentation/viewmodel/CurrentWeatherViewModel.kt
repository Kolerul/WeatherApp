package com.example.weatherapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.domain.repository.CurrentWeatherRepository
import com.example.weatherapp.presentation.uistate.WeatherUIState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException
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
            } catch (e: UnknownHostException) {
                _uiState.value = WeatherUIState.Error(R.string.internet_connection_error)
            } catch (e: HttpException) {
                _uiState.value = when (e.code()) {
                    401 -> WeatherUIState.Error(R.string.user_not_authorised)
                    404 -> WeatherUIState.Error(R.string.data_not_found)
                    else -> WeatherUIState.Error(R.string.unknown_error)
                }
            } catch (e: Exception) {
                _uiState.value = WeatherUIState.Error(R.string.unknown_error)
            }
        }
    }
}