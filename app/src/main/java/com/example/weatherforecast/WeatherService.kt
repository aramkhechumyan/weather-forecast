package com.example.weatherforecast

import com.example.weatherforecast.dto.WeatherDto
import retrofit2.Call
import retrofit2.http.GET

interface WeatherService {

    @GET("data/2.5/onecall?lat=33.441792&lon=-94.037689&appid=f134e6d1b5bf882ec11dd885003fcf29&exclude=minutely,current,hourly&units=metric")
    fun getWeather() : Call<WeatherDto>

}