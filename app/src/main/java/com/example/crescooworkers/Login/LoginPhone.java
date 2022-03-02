package com.example.crescooworkers.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crescooworkers.R;
import com.example.crescooworkers.Signup.SignUpOne;
import com.google.android.material.textfield.TextInputLayout;

public class LoginPhone extends AppCompatActivity {
    TextInputLayout phoneInputLayout;
    Button btnNext;
    TextView tapSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        phoneInputLayout = findViewById(R.id.phoneInputLayout);
        btnNext = findViewById(R.id.btnNext);
        tapSignUp = findViewById(R.id.tapSignUp);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneInputLayout.getEditText().getText().toString();
                Toast.makeText(LoginPhone.this, phone, Toast.LENGTH_SHORT).show();
               // Intent intent = new Intent(getApplicationContext(), LoginOTP.class);
               // startActivity(intent);
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