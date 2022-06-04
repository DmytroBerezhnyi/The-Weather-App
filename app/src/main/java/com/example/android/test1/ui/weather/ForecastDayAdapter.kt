package com.example.android.test1.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.test1.R
import com.example.android.test1.data.Weather
import com.squareup.picasso.Picasso

class ForecastDayAdapter : RecyclerView.Adapter<ForecastDayAdapter.ForecastDayViewHolder>() {

    private val adapterList = mutableListOf<ForecastDay>()

    fun submitList(forecastDay: ForecastDay) {
        this.adapterList.clear()
        repeat(7) {
            this.adapterList.add(forecastDay)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDayViewHolder {
        return ForecastDayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_weather, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ForecastDayViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int = adapterList.size

    inner class ForecastDayViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var textDate: TextView
        private lateinit var textText: TextView
        private lateinit var textMaxtemp: TextView
        private lateinit var textMintemp: TextView
        private lateinit var textAvgtemp: TextView
        private lateinit var textMaxwind: TextView
        private lateinit var textTotalprecip: TextView
        private lateinit var textSunrise: TextView
        private lateinit var textSunset: TextView
        private lateinit var textMoonrise: TextView
        private lateinit var textMoonset: TextView
        private lateinit var ivIcon: ImageView

        private fun View.setupViews() {
            textDate = findViewById(R.id.tvDate)
            textText = findViewById(R.id.tvText)
            textMaxtemp = findViewById(R.id.maxtemp_c)
            textMintemp = findViewById(R.id.mintemp_c)
            textAvgtemp = findViewById(R.id.avgtemp_c)
            textMaxwind = findViewById(R.id.maxwind_kph)
            textTotalprecip = findViewById(R.id.totalprecip_mm)
            textSunrise = findViewById(R.id.sunrise)
            textSunset = findViewById(R.id.sunset)
            textMoonrise = findViewById(R.id.moonrise)
            textMoonset = findViewById(R.id.moonset)
            ivIcon = findViewById(R.id.ivIcon)
        }

        fun bind(forecastDay: ForecastDay) {
            view.setupViews()
            textDate.text = forecastDay.observationTime
            textText.text = forecastDay.weatherDescriptions.firstOrNull().orEmpty()
            textMaxtemp.text = "Current temperature ${forecastDay.temperature} °C"
            textMintemp.text = "Current temperature ${forecastDay.temperature} °C"
            textAvgtemp.text = "Temperature feels like ${forecastDay.feelslike} °C"
            textMaxwind.text = "Air speed ${forecastDay.windSpeed} kph"
            textTotalprecip.text = "Mill of mercury ${forecastDay.pressure} mm"
            textSunrise.text = "Unknown"
            textSunset.text = "Unknown"
            textMoonrise.text = "Unknown"
            textMoonset.text = "Unknown"

            Picasso.with(view.context)
                .load(forecastDay.weatherIcons.first())
                .placeholder(R.drawable.sunrise)
                .error(R.drawable.sunrise)
                .into(ivIcon)
        }
    }
}