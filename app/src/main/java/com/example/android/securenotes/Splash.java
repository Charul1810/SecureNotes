package com.example.android.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

public class Splash extends AppCompatActivity {
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startLoadingData();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



    }
    private void startLoadingData() {
        // finish "loading data" in a random time between 1 and 3 seconds
        Random random = new Random();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoadingDataEnded();
            }
        }, 1000 + random.nextInt(2500));
    }
    private void onLoadingDataEnded()
    {
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));

        finish();
    }

}
