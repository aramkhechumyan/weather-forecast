package com.example.weatherforecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.dto.DayDto
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToLong

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {

    val days = mutableListOf<DayDto>()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val wetherData: TextView
        val wetherTemperature: TextView

        init {

            wetherData = view.findViewById(R.id.data_textView)
            wetherTemperature = view.findViewById(R.id.temperature_textView)
        }

        fun bindData(dayDto: DayDto) {
            val formatter = SimpleDateFormat("EEE.MMMdd")
            val dateString: String = formatter.format(Date(dayDto.dt * 1000))
            wetherData.text = "  " + dateString
            val tempNight = dayDto.temp.night.roundToLong().toString()
            val tempDay = dayDto.temp.day.roundToLong().toString()

            wetherTemperature.text = "  " + tempDay + "°" + " / " + tempNight + "°" + " C"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater
            .inflate(R.layout.weather_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(days[position])
    }


    override fun getItemCount(): Int {
        return days.size
    }

    fun addItems(days: List<DayDto>) {
        this.days.clear()
        this.days.addAll(days)
        notifyDataSetChanged()
    }
}