package com.example.crescooworkers.Signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crescooworkers.Login.LoginPhone;
import com.example.crescooworkers.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpTwo extends AppCompatActivity {

    TextInputLayout yearInputLayout, phoneInputLayout, hourInputLayout, dayInputLayout;
    TextView tapLogin;
    Button btnNext;

    public String years, phone, hour, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_two);

        yearInputLayout = findViewById(R.id.yearsInputLayout);
        phoneInputLayout = findViewById(R.id.phoneInputLayout);
        hourInputLayout = findViewById(R.id.hourInputLayout);
        dayInputLayout = findViewById(R.id.dayInputLayout);

        tapLogin = findViewById(R.id.tapLogin);

        btnNext = findViewById(R.id.btnNext);

        //get Values from last Activity
        Intent intent = getIntent();
        String uName = intent.getStringExtra("name");
        String item = intent.getStringExtra("selectedItem");


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //input validations
                if(!checkYears()||!checkPhone()||!checkHour()||!checkDay()){
                    return;
                }


                //get Values from EditTexts
               years = yearInputLayout.getEditText().getText().toString();
               phone = phoneInputLayout.getEditText().getText().toString();
               hour = hourInputLayout.getEditText().getText().toString();
               day = dayInputLayout.getEditText().getText().toString();

            }
        });

    }


    //input validations
    private boolean checkYears() {
        years = yearInputLayout.getEditText().getText().toString();

        if(years.isEmpty()){
            yearInputLayout.setError("Enter Your Years Of Experience");
            return false;
        }else {
            yearInputLayout.setError(null);
            return true;
        }
    }

    private boolean checkPhone() {
        phone = phoneInputLayout.getEditText().getText().toString();

        if(phone.isEmpty()){
            phoneInputLayout.setError("Enter Your Phone Number");
            return false;
        }else {
            phoneInputLayout.setError(null);
            return true;
        }
    }

    private boolean checkHour() {
        hour = hourInputLayout.getEditText().getText().toString();
        if(hour.isEmpty()){
            hourInputLayout.setError("Enter Your Hour Pay");
            return false;
        }else {
            hourInputLayout.setError(null);
            return true;
        }
    }

    private boolean checkDay() {
        day = dayInputLayout.getEditText().getText().toString();

        if(day.isEmpty()){
            dayInputLayout.setError("Enter Your Day Pay");
            return false;
        }else {
            dayInputLayout.setError(null);
            return true;
        }
    }

}