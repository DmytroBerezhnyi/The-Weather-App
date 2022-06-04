package com.example.android.test1.data

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Weather(
    @SerializedName("request") val request: Request,
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: Current
)