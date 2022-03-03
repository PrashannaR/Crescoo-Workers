package com.example.crescooworkers.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crescooworkers.R;
import com.example.crescooworkers.Signup.SignUpOne;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class LoginPhone extends AppCompatActivity {
    TextInputLayout phoneInputLayout;
    Button btnNext;
    TextView tapSignUp;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        phoneInputLayout = findViewById(R.id.phoneInputLayout);
        btnNext = findViewById(R.id.btnNext);
        tapSignUp = findViewById(R.id.tapSignUp);

        //Country Code
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneInputLayout.getEditText());



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String phone = phoneInputLayout.getEditText().getText().toString();
                //Toast.makeText(LoginPhone.this, ccp.getFullNumberWithPlus(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginOTP.class);
                intent.putExtra("phoneNumber", ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });

        tapSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpOne.class);
                startActivity(intent);
            }
        });
    }
}