package com.example.android.test1.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.android.test1.DataBase.WeatherDAO;
import com.example.android.test1.POJO.WeatherDB;

@Database(entities = {WeatherDB.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
        public abstract WeatherDAO weatherDAO();

    }
