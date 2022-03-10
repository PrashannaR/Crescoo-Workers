package com.example.crescooworkers.Signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crescooworkers.Login.LoginPhone;
import com.example.crescooworkers.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUpOne extends AppCompatActivity {
    TextInputLayout nameInputLayout,phoneInputLayout, genderInputLayout, ageInputLayout;
    CountryCodePicker ccp;
    TextView tapLogin;
    Button btnNext;

    public String phone;

    String tag = "SignUpOne";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_one);

        nameInputLayout = findViewById(R.id.nameInputLayout);
        phoneInputLayout = findViewById(R.id.phoneInputLayout);
        genderInputLayout = findViewById(R.id.genderInputLayout);
        ageInputLayout = findViewById(R.id.ageInputLayout);

        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneInputLayout.getEditText());

        tapLogin = findViewById(R.id.tapLogin);

        btnNext = findViewById(R.id.btnNext);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!checkName() ||!checkPhone() || !checkGender() || !checkAge()){
                    return;
                }

                String name = nameInputLayout.getEditText().getText().toString();
                String gender = genderInputLayout.getEditText().getText().toString();
                String age = ageInputLayout.getEditText().getText().toString();
                Intent intent = new Intent(getApplicationContext(), SignUpTwo.class);

                //send values to different Activity
                intent.putExtra("name", name);
                intent.putExtra("phone", ccp.getFullNumberWithPlus().replace(" ", ""));
                intent.putExtra("gender", gender);
                intent.putExtra("age", age);

                startActivity(intent);

            }
        });

        tapLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginPhone.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private boolean checkName() {
        String name = nameInputLayout.getEditText().getText().toString();

        if(name.isEmpty()){
            nameInputLayout.setError("Enter Your Name");
            return false;
        }else {
            nameInputLayout.setError(null);
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

    private boolean checkGender() {
        String gender = genderInputLayout.getEditText().getText().toString();

        if(gender.isEmpty()){
            genderInputLayout.setError("Enter Your Phone Number");
            return false;
        }else {
            genderInputLayout.setError(null);
            return true;
        }
    }

    private boolean checkAge() {
        String age = ageInputLayout.getEditText().getText().toString();

        if(age.isEmpty()){
            ageInputLayout.setError("Enter Your Phone Number");
            return false;
        }else {
            ageInputLayout.setError(null);
            return true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "onPause");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy");
    }
}