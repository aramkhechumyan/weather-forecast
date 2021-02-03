package com.example.weatherforecast

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.dto.DayDto
import com.example.weatherforecast.dto.WeatherDto
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class WeatherViewModel : ViewModel() {

    val weeklyLiveData = MutableLiveData<List<DayDto>>()

    fun getWeather() {

        val thread = Thread {

            val okHttpClient = OkHttpClient()
            val request: Request = Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&appid=f134e6d1b5bf882ec11dd885003fcf29&exclude=minutely,current,hourly&units=metric")
                .method("GET", null)
                .build()
            val response: Response = okHttpClient.newCall(request).execute()
            Thread.sleep(3000)
            Log.e(javaClass.simpleName, "=============================")
            // print(response.body?.string())
            val json = response.body?.string()


            val gson = Gson()
            val weatherDto: WeatherDto = gson.fromJson(json, WeatherDto::class.java)

            Handler(Looper.getMainLooper()).post {
                weeklyLiveData.value = weatherDto.daily
            }
        }

        thread.start()
/*
        val handlerThread = HandlerThread("Networking")
        val looper = handlerThread.looper
        val handler = Handler(looper)
        handler.post {
            val okHttpClient = OkHttpClient()
            val request: Request = Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&appid=f134e6d1b5bf882ec11dd885003fcf29&exclude=minutely,current,hourly&units=metric")
                .method("GET", null)
                .build()
            val response: Response = okHttpClient.newCall(request).execute()
            Thread.sleep(3000)
            print("=============================")
            // print(response.body?.string())
            val json = response.body?.string()


            val gson = Gson()
            val weatherDto: WeatherDto = gson.fromJson(json, WeatherDto::class.java)

            Handler(Looper.getMainLooper()).post {
                weeklyLiveData.value = weatherDto.daily
            }
        }*/

    }

/*    fun quit(){
        looper.quit()
    }*/
}