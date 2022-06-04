package com.example.android.test1.MVP

import com.hannesdorfmann.mosby.mvp.MvpView
import com.example.android.test1.data.WeatherDB

interface IMainWeatherView : MvpView {
    fun showList(weatherDBList: List<WeatherDB>)
}