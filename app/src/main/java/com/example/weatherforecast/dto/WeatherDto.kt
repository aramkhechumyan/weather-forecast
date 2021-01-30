package com.example.weatherforecast.dto

data class WeatherDto(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val daily: List<DayDto>
)