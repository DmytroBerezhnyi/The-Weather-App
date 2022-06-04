package com.example.android.test1.ui.weather

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.test1.ui.main.MainActivity
import com.example.android.test1.Adapters.APIService
import com.example.android.test1.data.Weather
import com.example.android.test1.R
import com.example.android.test1.data.WeatherDB
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : Activity() {
    private var tvCity: TextView? = null
    var recyclerView: RecyclerView? = null
    var city: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        init()
    }

    private fun init() {
        city = intent.getStringExtra("city")
        tvCity = findViewById(R.id.city)
        recyclerView = findViewById(R.id.recycler_forecast)
        tvCity!!.text = city
        getData(city, 7)
    }

    private fun getData(city: String?, n: Int) {
        val retrofit = Retrofit.Builder().baseUrl("http://api.weatherstack.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val apiService = retrofit.create(APIService::class.java)
        apiService.getCurrentCityWeather(KEY, city!!).enqueue(object : Callback<Weather?> {
            override fun onResponse(call: Call<Weather?>, response: Response<Weather?>) {
                try {
                    if (response.isSuccessful) {
                        val weather = buildList {
                            response.body()?.let { add(it) }
                        }
                        val builder = GsonBuilder()
                        val gson = builder.create()

                        adapter = ForecastDayAdapter()
                        recyclerView!!.adapter = adapter
                        adapter!!.submitList(weather.first().toForecastDay())
                        Thread {
                            val weatherDAO = MainActivity.getAppDatabase().weatherDAO()
                            weatherDAO.insert(
                                WeatherDB(Gson().toJson(weather.first().toForecastDay()), System.currentTimeMillis())
                            )
                            val count = weatherDAO.count
                            showToast(count)
                        }.start()
                    } else {
                        Toast.makeText(this@WeatherActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<Weather?>, t: Throwable) {
                println(t)
            }
        })
    }

    private fun showToast(count: Int) {
        runOnUiThread {
            Toast.makeText(this, "$count count of Ids", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private var adapter: ForecastDayAdapter? = null
        const val KEY = "08e3c7b834a2a15b2ed60405989b7324"
    }
}