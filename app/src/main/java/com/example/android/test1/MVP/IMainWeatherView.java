package com.example.android.test1.MVP;

import com.example.android.test1.data.WeatherDB;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

public interface IMainWeatherView extends MvpView {
    void showList(List<WeatherDB> weatherDBList);
}
