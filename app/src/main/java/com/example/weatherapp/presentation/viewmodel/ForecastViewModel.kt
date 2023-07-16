package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import com.example.weatherapp.presentation.uistate.ForecastUIState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException
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
            } catch (e: UnknownHostException) {
                _uiState.value = ForecastUIState.Error(R.string.internet_connection_error)
            } catch (e: HttpException) {
                _uiState.value = when (e.code()) {
                    401 -> ForecastUIState.Error(R.string.user_not_authorised)
                    404 -> ForecastUIState.Error(R.string.data_not_found)
                    else -> ForecastUIState.Error(R.string.unknown_error)
                }
            } catch (e: Exception) {
                _uiState.value = ForecastUIState.Error(R.string.unknown_error)
            }
        }
    }
}