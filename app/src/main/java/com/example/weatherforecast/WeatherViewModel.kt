package com.example.weatherforecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.dto.DayDto

class WeatherViewModel : ViewModel() {

    val weeklyLiveData = MutableLiveData<List<DayDto>>()
    val weatherRepository = WeatherRepository()

    fun getWeather() {

        weatherRepository.getWeather(object : WeatherRepository.WeatherCallback {
            override fun callback(daysDto: List<DayDto>) {
                weeklyLiveData.value = daysDto
            }
        })
    }
}
