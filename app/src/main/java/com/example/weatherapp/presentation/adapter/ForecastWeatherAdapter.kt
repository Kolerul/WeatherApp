package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.databinding.WeatherForecastViewHolderBinding
import com.example.weatherapp.domain.entity.ForecastWeather

class ForecastWeatherAdapter :
    ListAdapter<ForecastWeather, ForecastWeatherViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeatherViewHolder =
        ForecastWeatherViewHolder(
            WeatherForecastViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ForecastWeatherViewHolder, position: Int) {
        val current = getItem(position)
        holder.onBind(current)
    }

    object DiffCallback : DiffUtil.ItemCallback<ForecastWeather>() {
        override fun areItemsTheSame(oldItem: ForecastWeather, newItem: ForecastWeather): Boolean {
            return oldItem.weather[0].id == newItem.weather[0].id
        }

        override fun areContentsTheSame(
            oldItem: ForecastWeather,
            newItem: ForecastWeather
        ): Boolean {
            return oldItem == newItem
        }

    }
}