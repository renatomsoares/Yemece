package com.example.yemece.activity;

import android.app.Activity;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yemece.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                startActivity(new Intent(getApplicationContext(),ListImcsActivity.class));
                finish();
            }
        }).start();

    }
}
