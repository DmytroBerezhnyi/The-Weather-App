package com.example.android.test1.data

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Current(
    @SerializedName("observation_time") val observationTime: String,
    @SerializedName("temperature") val temperature: Int,
    @SerializedName("weather_code") val weatherCode: Int,
    @SerializedName("weather_icons") val weatherIcons: List<String>,
    @SerializedName("weather_descriptions") val weatherDescriptions: List<String>,
    @SerializedName("wind_speed") val windSpeed: Int,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("precip") val precip: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloudcover") val cloudcover: Int,
    @SerializedName("feelslike") val feelslike: Int,
    @SerializedName("uv_index") val uvIndex: Int,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("is_day") val isDay: String,
)