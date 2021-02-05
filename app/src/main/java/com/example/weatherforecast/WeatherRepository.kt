package com.example.weatherforecast


import com.example.weatherforecast.dto.DayDto
import com.example.weatherforecast.dto.WeatherDto
import retrofit2.Call

class WeatherRepository {

    interface WeatherCallback {
        fun callback(daysDto: List<DayDto>)
    }

    fun getWeather(weatherCallback: WeatherCallback) {

        val weatherApiService = RetrofitProvider()
        val weatherCall = weatherApiService.getWeatherApiService().getWeather()

        weatherCall.enqueue(object : retrofit2.Callback<WeatherDto>{
            override fun onResponse(
                call: Call<WeatherDto>,
                response: retrofit2.Response<WeatherDto>
            ) {
                if (response.isSuccessful){
                    val weatherDto = response.body()
                    weatherDto?.let {
                        weatherCallback.callback(it.daily)
                    }

                } else {

                }
            }

            override fun onFailure(call: Call<WeatherDto>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}