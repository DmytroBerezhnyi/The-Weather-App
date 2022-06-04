package com.example.android.test1.Adapters

import com.example.android.test1.data.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/current")
    fun getCurrentCityWeather(
        @Query("access_key") key: String,
        @Query("query") city: String
    ): Call<Weather>

    @GET("/forecast")
    fun getForecastCityWeather(
        @Query("access_key") key: String,
        @Query("query") city: String
    ): Call<Weather>
}