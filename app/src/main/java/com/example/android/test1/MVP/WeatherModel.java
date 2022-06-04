package com.example.android.test1.MVP;

import com.example.android.test1.data.WeatherDB;
import com.example.android.test1.ui.main.MainActivity;

import java.util.List;

public class WeatherModel {

    public List<WeatherDB> getAll() {
        return MainActivity.getAppDatabase().weatherDAO().getAll();
    }
}
