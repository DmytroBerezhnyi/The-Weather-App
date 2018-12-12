package com.example.android.test1.MVP;

import com.example.android.test1.MVP.IMainWeatherView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public interface IWeatherPresenter extends MvpPresenter<IMainWeatherView> {
    void buttonPressed();
}
