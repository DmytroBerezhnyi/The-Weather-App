package com.example.android.test1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.test1.Adapters.APIService;
import com.example.android.test1.Adapters.CustomAdapter;
import com.example.android.test1.DataBase.WeatherDAO;
import com.example.android.test1.POJO.Weather;
import com.example.android.test1.POJO.WeatherDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends Activity {
    private TextView tvCity;
    ListView listView;
    private  static CustomAdapter adapter;
    public static final String KEY = "7a949db346a74b029a891222180412";
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
    }

    private void init() {
        city = getIntent().getStringExtra("city");
        tvCity = findViewById(R.id.city);
        listView = findViewById(R.id.listView);

        tvCity.setText(city);
        getData(city, 7);
    }

    private void getData(String city, int n) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.apixu.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        Call<Weather> call = apiService.getForecastCityWeather(KEY, city, n);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                try {
                    if(response.isSuccess()) {
                        final Weather weather = response.body();
                        GsonBuilder builder = new GsonBuilder();
                        final Gson gson = builder.create();

                        adapter = new CustomAdapter(weather, getApplicationContext());
                        listView.setAdapter(adapter);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                               final WeatherDAO weatherDAO = MainActivity.getAppDatabase().weatherDAO();
                                weatherDAO.insert(new WeatherDB(gson.toJson(weather), System.currentTimeMillis()));
                                Log.d("TAG", gson.toJson(weather));
                                final int count = weatherDAO.getCount();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(WeatherActivity.this, count + " count of Ids", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).start();
                    }
                    else {
                        Toast.makeText(WeatherActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
    }
}


/*2018-12-06 18:23:13.649 31215-31243/com.example.android.test1 D/TAG: {"current":{"cloud":75,"condition":{"code":1003,"icon":"//cdn.apixu.com/weather/64x64/night/116.png","text":"Partly cloudy"},"feelslike_c":-5.1,"feelslike_f":22.8,"humidity":74,"is_day":0,"last_updated":"2018-12-06 18:15","last_updated_epoch":1544112910,"precip_in":0.0,"precip_mm":0.0,"pressure_in":30.7,"pressure_mb":1022.0,"temp_c":0.0,"temp_f":32.0,"uv":2.0,"vis_km":10.0,"vis_miles":6.0,"wind_degree":330,"wind_dir":"NNW","wind_kph":19.1,"wind_mph":11.9},"forecast":{"forecastday":[{"astro":{"moonrise":"06:19 AM","moonset":"03:46 PM","sunrise":"07:43 AM","sunset":"03:55 PM"},"date":"2018-12-06","date_epoch":1544054400,
"day":{"avghumidity":91.0,"avgtemp_c":-1.7,"avgtemp_f":28.9,"avgvis_km":15.8,"avgvis_miles":9.0,"condition":{"code":1198,"icon":"//cdn.apixu.com/weather/64x64/day/311.png","text":"Light freezing rain"},"maxtemp_c":-0.8,"maxtemp_f":30.6,"maxwind_kph":18.4,"maxwind_mph":11.4,"mintemp_c":-4.9,"mintemp_f":23.2,"totalprecip_in":0.02,"totalprecip_mm":0.4,"uv":0.6}},{"astro":{"moonrise":"07:27 AM","moonset":"04:21 PM","sunrise":"07:44 AM","sunset":"03:55 PM"},"date":"2018-12-07","date_epoch":1544140800,"day":{"avghumidity":89.0,"avgtemp_c":-3.9,"avgtemp_f":25.0,"avgvis_km":20.0,"avgvis_miles":12.0,"condition":{"code":1003,"icon":"//cdn.apixu.com/weather/64x64/day/116.png","text":"Partly cloudy"},"maxtemp_c":-2.1,"maxtemp_f":28.2,"maxwind_kph":13.3,"maxwind_mph":8.3,"mintemp_c":-7.3,"mintemp_f":18.9,"totalprecip_in":0.0,"totalprecip_mm":0.0,"uv":0.8}},{"astro":{"moonrise":"08:28 AM","moonset":"05:02 PM","sunrise":"07:45 AM","sunset":"03:55 PM"},"date":"2018-12-08","date_epoch":1544227200,"day":{"avghumidity":90.0,"avgtemp_c":-2.1,"avgtemp_f":28.2,"avgvis_km":15.6,"avgvis_miles":9.0,"condition":{"code":1153,"icon":"//cdn.apixu.com/weather/64x64/day/266.png","text":"Light drizzle"},"maxtemp_c":0.3,"maxtemp_f":32.5,"maxwind_kph":19.4,"maxwind_mph":12.1,"mintemp_c":-7.7,"mintemp_f":18.1,"totalprecip_in":0.09,"totalprecip_mm":2.2,"uv":0.5}},
{"astro":{"moonrise":"09:24 AM","moonset":"05:50 PM","sunrise":"07:46 AM","sunset":"03:54 PM"},"date":"2018-12-09","date_epoch":1544313600,
"day":{"avghumidity":89.0,"avgtemp_c":-0.8,"avgtemp_f":30.6,"avgvis_km":13.9,"avgvis_miles":8.0,"condition":{"code":1216,"icon":"//cdn.apixu.com/weather/64x64/day/329.png","text":"Patchy moderate snow"},"maxtemp_c":1.0,"maxtemp_f":33.8,"maxwind_kph":19.8,"maxwind_mph":12.3,"mintemp_c":-6.7,"mintemp_f":19.9,"totalprecip_in":0.04,"totalprecip_mm":0.9,"uv":0.6}},{"astro":{"moonrise":"10:10 AM","moonset":"06:44 PM","sunrise":"07:47 AM","sunset":"03:54 PM"},"date":"2018-12-10","date_epoch":1544400000,
"day":{"avghumidity":93.0,"avgtemp_c":-0.9,"avgtemp_f":30.4,"avgvis_km":18.5,"avgvis_miles":11.0,"condition":{"code":1153,"icon":"//cdn.apixu.com/weather/64x64/day/266.png","text":"Light drizzle"},"maxtemp_c":1.0,"maxtemp_f":33.8,"maxwind_kph":19.4,"maxwind_mph":12.1,"mintemp_c":-8.4,"mintemp_f":16.9,"totalprecip_in":0.18,"totalprecip_mm":4.6,"uv":0.6}},{"astro":{"moonrise":"10:50 AM","moonset":"07:43 PM","sunrise":"07:48 AM","sunset":"03:54 PM"},"date":"2018-12-11","date_epoch":1544486400,"day":{"avghumidity":95.0,"avgtemp_c":0.8,"avgtemp_f":33.5,"avgvis_km":12.7,"avgvis_miles":7.0,"condition":{"code":1225,"icon":"//cdn.apixu.com/weather/64x64/day/338.png","text":"Heavy snow"},"maxtemp_c":1.1,"maxtemp_f":34.0,"maxwind_kph":20.9,"maxwind_mph":13.0,"mintemp_c":-1.8,"mintemp_f":28.8,"totalprecip_in":0.56,"totalprecip_mm":14.2,"uv":39960.0}},{"astro":{"moonrise":"11:23 AM","moonset":"08:44 PM","sunrise":"07:49 AM","sunset":"03:54 PM"},"date":"2018-12-12","date_epoch":1544572800,"day":{"avghumidity":93.0,"avgtemp_c":-0.8,"avgtemp_f":30.6,"avgvis_km":12.9,"avgvis_miles":8.0,"condition":{"code":1003,"icon":"//cdn.apixu.com/weather/64x64/day/116.png","text":"Partly cloudy"},"maxtemp_c":1.0,"maxtemp_f":33.8,"maxwind_kph":13.0,"maxwind_mph":8.1,"mintemp_c":-2.0,"mintemp_f":28.4,"totalprecip_in":0.0,"totalprecip_mm":0.0,"uv":39960.0}}]},"location":{"country":"Украина","lat":50.43,"localtime":"2018-12-06 18:23","localtime_epoc
2018-12-*/