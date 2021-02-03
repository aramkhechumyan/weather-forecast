package com.example.weatherforecast

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {
    val weatherViewModel by viewModels<WeatherViewModel>()
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

        weatherViewModel.weeklyLiveData.observe(this) {
            weatherAdapter.addItems(it)
        }

        weatherViewModel.getWeather()

        /*val runnable = Runnable { weatherViewModel.getWeather() }

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, 5000)

        handler.removeCallbacks(runnable)*/
    }
}