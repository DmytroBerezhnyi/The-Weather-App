package com.example.android.test1.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class WeatherDB {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String jsonObject;
    public  long time;

    public WeatherDB(String jsonObject, long time) {
        this.jsonObject = jsonObject;
        this.time = time;
    }
}
