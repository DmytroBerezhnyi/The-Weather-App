package com.example.android.test1;

import com.example.android.test1.POJO.WeatherDB;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

public interface IMainWeatherView extends MvpView {
    void showList(List<WeatherDB> weatherDBList);
}
