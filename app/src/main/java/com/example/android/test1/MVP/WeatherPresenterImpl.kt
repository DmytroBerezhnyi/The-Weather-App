package com.example.android.test1.MVP

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.example.android.test1.MVP.IMainWeatherView
import com.example.android.test1.MVP.IWeatherPresenter
import com.example.android.test1.MVP.WeatherModel
import com.example.android.test1.data.WeatherDB

class WeatherPresenterImpl : MvpBasePresenter<IMainWeatherView?>(), IWeatherPresenter {

    private val weatherModel: WeatherModel = WeatherModel()
    private var buttonPressed = false

    override fun buttonPressed() {
        buttonPressed = true
        Thread {
            val list = weatherModel.all
            view!!.showList(list)
        }.start()
    }
}