package com.example.android.test1.DataBase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.test1.data.WeatherDB;

import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weatherdb")
    public List<WeatherDB> getAll();

    @Query("SELECT * FROM weatherdb WHERE id = :id")
    public WeatherDB getById(long id);

    @Query("SELECT COUNT(id) FROM weatherdb")
    public int getCount();

    @Insert
    public void insert(WeatherDB weatherDB);

    @Update
    public void update(WeatherDB weatherDB);

    @Delete
    public void delete(WeatherDB weatherDB);

}