package com.example.android.test1;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] cities;

    private ListView listView;
    private android.support.v7.widget.Toolbar toolbar;
    private ImageView imageViewNavigation;
    private DrawerLayout mDrawer;
    //private NavigationView nvDrawer;
    private LinearLayout linearLayout;

    private RotateAnimation rotateAnimation;
    private ArrayAdapter<String> mAdapter;

    public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return super.onTouchEvent(event);
    }

    public void displayMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void init() {
        listView = findViewById(R.id.listViewMainActivity);
        toolbar = findViewById(R.id.my_toolbar);
        imageViewNavigation = findViewById(R.id.ivNavigation);
        linearLayout = findViewById(R.id.linearLayout);
        mDrawer = findViewById(R.id.drawer_layout);

        cities = getResources().getStringArray(R.array.string_array_cities);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        listView.setAdapter(mAdapter);

        clickListeners();
        touchListeners();
        animView();

        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").build();
    }

    private void clickListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);

                intent.putExtra("city", cities[position]);
                startActivity(intent);
            }
        });

        imageViewNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDrawer.isDrawerOpen(GravityCompat.START)) {
                    mDrawer.openDrawer(Gravity.START);
                } else {
                    mDrawer.openDrawer(Gravity.NO_GRAVITY);
                }
            }
        });

    }

    @SuppressLint("ClickableViewAccessibility")
    private void touchListeners() {
        //listView.performClick();
        View.OnTouchListener onSwipeTouchListener = new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
                displayMessage("top");
            }
            public void onSwipeRight() {
                imageViewNavigation.startAnimation(rotateAnimation);
                mDrawer.openDrawer(Gravity.START);
                displayMessage("right");
            }
            public void onSwipeLeft() {
                displayMessage("left");
                mDrawer.openDrawer(Gravity.NO_GRAVITY);
                //mDrawer.closeDrawers();
            }
            public void onSwipeBottom() {
                displayMessage("bottom");
            }
        };

        linearLayout.setOnTouchListener(onSwipeTouchListener);
        listView.setOnTouchListener(onSwipeTouchListener);
    }

    private void animView() {
        rotateAnimation = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setInterpolator(new LinearInterpolator());
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
    }