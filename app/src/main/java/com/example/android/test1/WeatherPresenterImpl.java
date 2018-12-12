package com.example.android.test1;

import com.example.android.test1.POJO.WeatherDB;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

public class WeatherPresenterImpl extends MvpBasePresenter<IMainWeatherView> implements IWeatherPresenter, WeatherModel.GettingData {

    private final WeatherModel weatherModel;
    private boolean buttonPressed = false;

    public WeatherPresenterImpl() {
        weatherModel = new WeatherModel(this);
    }

    @Override
    public void buttonPressed() {
        buttonPressed = true;
        weatherModel.getAll();
    }

    @Override
    public void getAll(List<WeatherDB> weatherDBList) {
        if (buttonPressed) {
            if (isViewAttached()) {
                getView().showList(weatherDBList);
                buttonPressed = false;
            }
        }
    }
}