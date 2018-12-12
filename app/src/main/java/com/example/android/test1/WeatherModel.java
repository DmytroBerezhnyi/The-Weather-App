package com.example.android.test1;

import com.example.android.test1.POJO.WeatherDB;

import java.util.List;

public class WeatherModel {

    public List<WeatherDB> getAll() {
        return MainActivity.getAppDatabase().weatherDAO().getAll();
    }
}
