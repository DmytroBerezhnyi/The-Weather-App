package com.example.android.test1.MVP;

import com.example.android.test1.data.WeatherDB;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

public class WeatherPresenterImpl extends MvpBasePresenter<IMainWeatherView> implements IWeatherPresenter {

    private final WeatherModel weatherModel;
    private boolean buttonPressed = false;

    public WeatherPresenterImpl() {
        weatherModel = new WeatherModel();
    }

    @Override
    public void buttonPressed() {
        buttonPressed = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<WeatherDB> list = weatherModel.getAll();
                getView().showList(list);
            }
        }).start();

    }
}