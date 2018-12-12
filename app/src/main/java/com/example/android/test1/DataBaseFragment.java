package com.example.android.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.example.android.test1.Adapters.CustomAdapterEventBus;
import com.example.android.test1.POJO.WeatherDB;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.List;

public class DataBaseFragment extends MvpFragment<IMainWeatherView, IWeatherPresenter> implements IMainWeatherView {

    private Button button;
    private ListView listView;
    private CustomAdapterEventBus customAdapterEventBus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        button = getView().findViewById(R.id.myBtnGetEventBus);
        listView = getView().findViewById(R.id.myListViewEventBus);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getPresenter().buttonPressed();
                            }
                        });
                    }
                }).start();
            }
        });
    }

    @Override
    public void showList(final List<WeatherDB> weatherDBList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                customAdapterEventBus = new CustomAdapterEventBus(weatherDBList, getContext());
                listView.setAdapter(customAdapterEventBus);
                customAdapterEventBus.notifyDataSetChanged();
            }
        });
    }

    @Override
    public IWeatherPresenter createPresenter() {
        return new WeatherPresenterImpl();
    }
}
