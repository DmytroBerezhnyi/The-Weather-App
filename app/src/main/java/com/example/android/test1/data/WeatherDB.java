package com.example.android.test1.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public long getId() {
        return id;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public long getTime() {
        return time;
    }

}