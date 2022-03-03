package com.example.crescooworkers.Signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
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
    TextInputLayout nameInputLayout,phoneInputLayout;
    CountryCodePicker ccp;
    TextView tapLogin;
    Button btnNext;

    public String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_one);

        nameInputLayout = findViewById(R.id.nameInputLayout);
        phoneInputLayout = findViewById(R.id.phoneInputLayout);

        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneInputLayout.getEditText());

        tapLogin = findViewById(R.id.tapLogin);

        btnNext = findViewById(R.id.btnNext);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!checkName() ||!checkPhone()){
                    return;
                }

                String name = nameInputLayout.getEditText().getText().toString();
               // phone = phoneInputLayout.getEditText().getText().toString();

                Intent intent = new Intent(getApplicationContext(), SignUpOTP.class);

                //send values to different Activity
                intent.putExtra("name", name);
                intent.putExtra("phone", ccp.getFullNumberWithPlus().replace(" ", ""));

                startActivity(intent);

            }
        });

        tapLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginPhone.class);
                startActivity(intent);
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
}