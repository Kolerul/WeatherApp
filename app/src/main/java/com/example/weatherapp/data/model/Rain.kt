package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName


data class Rain(
    @SerializedName("1h") val rainVolumeForOneHour: Double?,
    @SerializedName("3h") val rainVolumeForThreeHours: Double?
)