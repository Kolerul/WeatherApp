package com.example.weatherapp.presentation.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherForecastViewHolderBinding
import com.example.weatherapp.domain.entity.ForecastWeather

class ForecastWeatherViewHolder(private val binding: WeatherForecastViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(weather: ForecastWeather) {
        binding.apply {
            dateInfo.text = weather.timeOfDataForecasted
            humidityText.text = weather.main.humidity.toString()
            degrees.text = binding.root.context.getString(
                R.string.degrees,
                (weather.main.temperature - 273.15).toInt().toString()
            )

            Log.d("Forecast", weather.weather[0].main)

            when (weather.weather[0].main) {
                "Clear" -> {
                    if (weather.sys.partOfTheDay == "d") weatherImage.setImageResource(R.drawable.ic_sunny)
                    else weatherImage.setImageResource(R.drawable.ic_moon)
                }

                "Clouds" -> weatherImage.setImageResource(R.drawable.ic_cloud)
                "Rain" -> weatherImage.setImageResource(R.drawable.ic_rain)
                "Snow" -> weatherImage.setImageResource(R.drawable.ic_snow)
                "Extreme" -> weatherImage.setImageResource(R.drawable.ic_flashlight)
            }
        }
    }

}