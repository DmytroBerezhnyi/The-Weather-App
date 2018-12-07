package com.example.android.test1;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.test1.Adapters.CustomAdapterEventBus;
import com.example.android.test1.POJO.WeatherDB;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class DataBaseActivity extends AppCompatActivity {

    DataBaseFragment dataBaseFragment;
    FragmentTransaction fragmentTransaction;
    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        eventBus = EventBus.getDefault();
        eventBus.register(this);

        dataBaseFragment = new DataBaseFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frgmCont, dataBaseFragment);
        fragmentTransaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(List<WeatherDB> event){
        Toast.makeText(this, "Hello from Activity", Toast.LENGTH_SHORT).show();
    }

}
