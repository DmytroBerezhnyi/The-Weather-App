package com.example.android.test1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.test1.POJO.WeatherDB;

import java.util.List;

@Dao
public interface WeatherDAO {

    @Query("SELECT * FROM weatherdb")
    List<WeatherDB> getAll();

    @Query("SELECT * FROM weatherdb WHERE id = :id")
    WeatherDB getById(long id);

    @Query("SELECT COUNT(id) FROM weatherdb")
    int getCount();

    @Insert
    void insert(WeatherDB weatherDB);

    @Update
    void update(WeatherDB weatherDB);

    @Delete
    void delete(WeatherDB weatherDB);

}