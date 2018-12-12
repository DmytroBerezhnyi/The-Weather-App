package com.example.android.test1;

import com.example.android.test1.POJO.WeatherDB;

import java.util.List;

public class WeatherModel {
    GettingData gettingData;

    public WeatherModel(GettingData gettingData) {
        this.gettingData = gettingData;
    }

    public void getAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                gettingData.getAll(MainActivity.getAppDatabase().weatherDAO().getAll());
            }
        }).start();
    }

    public interface GettingData {
        void getAll(List<WeatherDB> weatherDBList);
        }
}
