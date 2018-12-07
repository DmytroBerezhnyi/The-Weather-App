package com.example.android.test1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.test1.Adapters.CustomAdapterEventBus;
import com.example.android.test1.POJO.WeatherDB;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class DataBaseFragment extends Fragment {

    private Button button;
    private ListView listView;
    private CustomAdapterEventBus customAdapterEventBus;
    private EventBus eventBus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_data_base, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        button = getView().findViewById(R.id.myBtnGetEventBus);
        listView = getView().findViewById(R.id.myListViewEventBus);

        eventBus = EventBus.getDefault();
        eventBus.register(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<WeatherDB> weatherDBList = MainActivity.getAppDatabase().weatherDAO().getAll();
                        eventBus.post(weatherDBList);
                    }
                }).start();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(List<WeatherDB> event){
        button.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        customAdapterEventBus = new CustomAdapterEventBus(event, getContext());
        listView.setAdapter(customAdapterEventBus);
    }

}
