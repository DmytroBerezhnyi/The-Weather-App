package com.example.android.test1;

import org.greenrobot.eventbus.EventBus;

public class WeatherModel {
    private EventBus eventBus;

    public WeatherModel() {
        eventBus = EventBus.getDefault();
    }

    public void getAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                eventBus.post(MainActivity.getAppDatabase().weatherDAO().getAll());
            }
        }).start();
    }

}
