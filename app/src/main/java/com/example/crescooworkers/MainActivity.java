package com.example.crescooworkers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.crescooworkers.Login.LoginPhone;
import com.example.crescooworkers.Signup.SignUpOne;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SignUpOne.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }
}