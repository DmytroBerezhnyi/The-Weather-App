package com.example.android.test1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private  static  CustomAdapter adapter;
    public static final String KEY = "7a949db346a74b029a891222180412";
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    private void init() {
        city = getIntent().getStringExtra("city");
        tvCity = findViewById(R.id.city);
        listView = findViewById(R.id.listView);

        tvCity.setText(city);
        getData(city, 10);
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
