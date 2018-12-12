package com.example.android.test1;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public interface IWeatherPresenter extends MvpPresenter<IMainWeatherView> {
    void buttonPressed();
    void refresData();
}
