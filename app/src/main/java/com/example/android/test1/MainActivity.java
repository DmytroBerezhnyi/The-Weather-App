package com.example.android.test1;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
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
    private LinearLayout linearLayout;
    private ArrayAdapter<String> mAdapter;
    public static AppDatabase appDatabase;
    RotateAnimation rotateAnimation;

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

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        listView = findViewById(R.id.listViewMainActivity);
        toolbar = findViewById(R.id.my_toolbar);
        imageViewNavigation = findViewById(R.id.ivNavigation);
        linearLayout = findViewById(R.id.linearLayout);

        cities = getResources().getStringArray(R.array.string_array_cities);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        listView.setAdapter(mAdapter);

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
                Toast.makeText(MainActivity.this, "Navigation image was been clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //listView.performClick();

        View.OnTouchListener onSwipeTouchListener = new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
                displayMessage("top");
            }
            public void onSwipeRight() {
                imageViewNavigation.startAnimation(rotateAnimation);
                displayMessage("right");
            }
            public void onSwipeLeft() {
                displayMessage("left");
            }
            public void onSwipeBottom() {
                displayMessage("bottom");
            }
        };

        linearLayout.setOnTouchListener(onSwipeTouchListener);
        listView.setOnTouchListener(onSwipeTouchListener);

        rotateAnimation = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setInterpolator(new LinearInterpolator());


        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").build();
        }
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

   private class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }
    }

    }