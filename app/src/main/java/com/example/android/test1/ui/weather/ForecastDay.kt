package com.example.android.test1.ui.weather

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.test1.data.Weather

@Entity
data class ForecastDay(
    @PrimaryKey var id: Int = -1,
    val observationTime: String,
    val temperature: Int,
    val weatherCode: Int,
    val weatherIcons: List<String>,
    val weatherDescriptions: List<String>,
    val windSpeed: Int,
    val windDegree: Int,
    val windDir: String,
    val pressure: Int,
    val precip: Int,
    val humidity: Int,
    val cloudcover: Int,
    val feelslike: Int,
    val uvIndex: Int,
    val visibility: Int,
    val isDay: String,
)

fun Weather.toForecastDay(): ForecastDay {
    return ForecastDay(
        observationTime = current.observationTime,
        temperature = current.temperature,
        feelslike = current.feelslike,
        weatherCode = current.weatherCode,
        weatherIcons = current.weatherIcons,
        weatherDescriptions = current.weatherDescriptions,
        windSpeed = current.windSpeed,
        windDegree = current.windDegree,
        windDir = current.windDir,
        pressure = current.pressure,
        precip = current.precip,
        humidity = current.humidity,
        cloudcover = current.cloudcover,
        uvIndex = current.uvIndex,
        visibility = current.visibility,
        isDay = current.isDay,
    )
}