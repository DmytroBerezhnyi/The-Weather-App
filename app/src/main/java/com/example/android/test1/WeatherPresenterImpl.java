package com.example.android.test1;

import com.example.android.test1.POJO.WeatherDB;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;

public class WeatherPresenterImpl extends MvpBasePresenter<IMainWeatherView> implements IWeatherPresenter {
    private final WeatherModel weatherModel;
    private EventBus eventBus;
    private List<WeatherDB> event;

    public WeatherPresenterImpl() {
        weatherModel = new WeatherModel();
        weatherModel.getAll();
        eventBus = EventBus.getDefault();
        eventBus.register(this);
    }

    @Override
    public void buttonPressed() {
        if(isViewAttached()) {
            getView().showList(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(List<WeatherDB> event){
        this.event = event;
    }
}