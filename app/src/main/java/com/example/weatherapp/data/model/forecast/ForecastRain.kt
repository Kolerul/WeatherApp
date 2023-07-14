package com.example.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastRain(
    @SerializedName("3h") val rainVolumeForThreeHours: Double?
)