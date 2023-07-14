package com.example.weatherapp.data.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastSnow(
    @SerializedName("3h") val snowVolumeForThreeHours: Double?
)