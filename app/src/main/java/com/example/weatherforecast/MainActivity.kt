package com.example.weatherforecast

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.dto.WeatherDto
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MainActivity : AppCompatActivity() {

    val weatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutInflater
        val weather: RecyclerView = findViewById(R.id.weather)

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        weather.layoutManager = linearLayoutManager
        weather.adapter = weatherAdapter

        val thread = Thread(Runnable {

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
                weatherAdapter.addItems(weatherDto.daily)
            }
        })

        thread.start()

    }
}