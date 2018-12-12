package com.example.android.test1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.android.test1.Adapters.CustomAdapterEventBus;
import com.example.android.test1.POJO.WeatherDB;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import java.util.List;

public class DataBaseActivity extends MvpActivity<IMainWeatherView, IWeatherPresenter> implements IMainWeatherView {

    private Button button;
    private ListView listView;
    private CustomAdapterEventBus customAdapterEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_data_base);
        init();
    }

    private void init() {
        button = findViewById(R.id.myBtnGetEventBus);
        listView = findViewById(R.id.myListViewEventBus);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().buttonPressed();
            }
        });
    }

    @Override
    public void showList(List<WeatherDB> weatherDBList) {
        button.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        customAdapterEventBus = new CustomAdapterEventBus(weatherDBList, this);
        listView.setAdapter(customAdapterEventBus);
        customAdapterEventBus.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IWeatherPresenter createPresenter() {
        return new WeatherPresenterImpl();
    }
}
