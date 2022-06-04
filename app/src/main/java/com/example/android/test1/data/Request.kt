package com.example.android.test1.data

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Request(
    @SerializedName("type") @Expose val type: String,
    @SerializedName("query") @Expose val query: String,
    @SerializedName("language") @Expose val language: String,
    @SerializedName("unit") @Expose val unit: String
)