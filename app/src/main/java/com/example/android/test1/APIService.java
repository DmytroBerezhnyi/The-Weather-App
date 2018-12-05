package com.example.android.test1;

import com.example.android.test1.POJO.Weather;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @POST("current.json")
    Call<Weather> getCurrentCityWeather(@Query("key") String key,
                                 @Query("q") String city);

    @POST("forecast.json")
    Call<Weather> getForecastCityWeather(@Query("key") String key,
                                        @Query("q") String city,
                                         @Query("days") int days);
}
