package com.example.android.test1.DataBase

import androidx.room.Database
import com.example.android.test1.data.WeatherDB
import androidx.room.RoomDatabase

@Database(entities = [WeatherDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    public abstract fun weatherDAO(): WeatherDao
}