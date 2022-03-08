package com.example.crescooworkers.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.crescooworkers.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    FirebaseAuth auth;
    public String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!= null){
            phone = auth.getCurrentUser().getPhoneNumber();
        }

        //navBar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                       // startActivity(new Intent(getApplicationContext(), Profiles.class));
                        Intent intent = new Intent(getApplicationContext(), Profiles.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                }
                return false;
            }
        });
    }
}