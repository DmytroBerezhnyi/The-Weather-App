package com.example.android.test1;

import com.example.android.test1.POJO.WeatherDB;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import java.util.List;

public class WeatherPresenterImpl extends MvpBasePresenter<IMainWeatherView> implements IWeatherPresenter, WeatherModel.GettingData {

    private final WeatherModel weatherModel;
    private List<WeatherDB> event;
    private boolean refresh = true;

    public WeatherPresenterImpl() {
        weatherModel = new WeatherModel(this);
        refresData();
    }

    @Override
    public void buttonPressed() {
        if (isViewAttached()) {
            getView().showList(event);
        }
    }

    @Override
    public void refresData() {
        weatherModel.getAll();
        refresh = true;
    }

    @Override
    public void getAllObj(List<WeatherDB> weatherDBList) {
        this.event = weatherDBList;

        if(!refresh) {
            buttonPressed();
            refresh = !refresh;
        }
    }
}