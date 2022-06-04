package com.example.android.test1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WeatherDB(var jsonObject: String, var time: Long) {
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}