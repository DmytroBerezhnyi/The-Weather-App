package com.example.android.test1;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] cities;

    private ListView listView;
    private FrameLayout toolbar;
    private ImageView imageViewNavigation;
    private RelativeLayout relativeLayout;
    private LinearLayout mainMenu;
    private FrameLayout menu_background;
    private FrameLayout fragment_content;
    private TextView menu_item1;

    private RotateAnimation rotateAnimation;
    private ArrayAdapter<String> mAdapter;

    public static AppDatabase appDatabase;

    private Animation animationTo;
    private Animation animationFrom;
    private Animation animationToMenuBackground;
    private Animation animationFromMenuBackground;
    private ImageView ivTools;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
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
        toolbar = findViewById(R.id.toolbar);
        imageViewNavigation = findViewById(R.id.ivNavigation);
        relativeLayout = findViewById(R.id.drawer_layout);
        mainMenu = findViewById(R.id.main_menu);
        menu_item1 = findViewById(R.id.menu_item1);
        menu_background = findViewById(R.id.menu_background);
        fragment_content = findViewById(R.id.fragment_container);
        ivTools = findViewById(R.id.ivTools);

        cities = getResources().getStringArray(R.array.string_array_cities);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        listView.setAdapter(mAdapter);

        clickListeners();
        touchListeners();
        animView();

        animationFrom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_from);
        animationTo = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_to);
        animationFromMenuBackground = AnimationUtils.loadAnimation(MainActivity.this, R.anim.menu_background_alpha_from);
        animationToMenuBackground = AnimationUtils.loadAnimation(MainActivity.this, R.anim.menu_background_alpha_to);

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
                if (mainMenu.getVisibility() == View.GONE) {
                    openCustomDrawer();
                } else if (mainMenu.getVisibility() == View.VISIBLE) {
                    closeCustomDrawer();
                }
            }
        });

        menu_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Its was been clicked", Toast.LENGTH_SHORT).show();
            }
        });

        ivTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataBaseActivity.class);
                startActivity(intent);
            }
        });

    }

    private void closeCustomDrawer() {
        mainMenu.startAnimation(animationFrom);
        menu_background.startAnimation(animationFromMenuBackground);
        //listView.setEnabled(true);
        mainMenu.setVisibility(View.GONE);
        menu_background.setVisibility(View.GONE);
    }

    private void openCustomDrawer() {
        mainMenu.setVisibility(View.VISIBLE);
        menu_background.setVisibility(View.VISIBLE);
        //listView.setEnabled(false);
        mainMenu.startAnimation(animationTo);
        menu_background.startAnimation(animationToMenuBackground);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void touchListeners() {
        //listView.performClick();
        View.OnTouchListener onSwipeTouchListener = new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
                displayMessage("top");
            }
            public void onSwipeRight() {
                    displayMessage("right");
                if (mainMenu.getVisibility() == View.GONE) {
                   openCustomDrawer();
                }
            }
            //DO NOT WORK SWIPE TO LEFT IF LIST VIEW IS NOT ENABLED
            public void onSwipeLeft() {
                displayMessage("left");
                if (mainMenu.getVisibility() == View.VISIBLE) {
                    closeCustomDrawer();
                }
            }
            public void onSwipeBottom() {
                displayMessage("bottom");
            }
        };

        relativeLayout.setOnTouchListener(onSwipeTouchListener);
        listView.setOnTouchListener(onSwipeTouchListener);
        menu_background.setOnTouchListener(onSwipeTouchListener);

    }

    private void animView() {
        rotateAnimation = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(250);
        rotateAnimation.setInterpolator(new LinearInterpolator());
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public void onBackPressed() {
        if(mainMenu.getVisibility() == View.VISIBLE) {
            closeCustomDrawer();
        } else {
            super.onBackPressed();
        }
    }
}