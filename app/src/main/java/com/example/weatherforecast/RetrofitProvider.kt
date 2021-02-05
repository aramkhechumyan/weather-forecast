package com.example.weatherforecast

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    fun getWeatherApiService(): WeatherService {
        return getRetrofit().create(WeatherService::class.java)
    }
}