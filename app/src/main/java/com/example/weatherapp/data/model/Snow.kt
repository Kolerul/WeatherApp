package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("1h") val snowVolumeForOneHour: Double?,
    @SerializedName("3h") val snowVolumeForThreeHours: Double?
)