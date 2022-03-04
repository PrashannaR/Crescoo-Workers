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

import com.example.crescooworkers.Activities.Home;
import com.example.crescooworkers.Constructor.Constructor;
import com.example.crescooworkers.Login.LoginPhone;
import com.example.crescooworkers.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpTwo extends AppCompatActivity {

    TextInputLayout yearInputLayout, phoneInputLayout, hourInputLayout, dayInputLayout, menu;
    AutoCompleteTextView dropdown_menu;
    TextView tapLogin;
    Button btnNext;

    FirebaseAuth auth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public String years, hour, day, selectedItem, name, phone;

    String tag = "SignUp Two";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_two);

        yearInputLayout = findViewById(R.id.yearsInputLayout);
        phoneInputLayout = findViewById(R.id.phoneInputLayout);
        hourInputLayout = findViewById(R.id.hourInputLayout);
        dayInputLayout = findViewById(R.id.dayInputLayout);

        menu = findViewById(R.id.menu);
        dropdown_menu = findViewById(R.id.dropdown_menu);

        tapLogin = findViewById(R.id.tapLogin);

        btnNext = findViewById(R.id.btnNext);

        //firebase
        auth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();

        //getValues

        Intent otpIntent = getIntent();
        name = otpIntent.getStringExtra("name");
        phone = otpIntent.getStringExtra("phone");





        //drop down menu items
        String[] items = {"Item1", "Item2", "Item69"};
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(SignUpTwo.this, R.layout.drop_down_list,items);
        dropdown_menu.setAdapter(itemAdapter);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //input validations
                if(!checkYears()||!checkHour()||!checkDay()){
                    return;
                }


                //get Values from EditTexts
               years = yearInputLayout.getEditText().getText().toString();
               hour = hourInputLayout.getEditText().getText().toString();
               day = dayInputLayout.getEditText().getText().toString();

               //database
                reference = rootNode.getReference("workers");

                Constructor constructor = new Constructor(name, selectedItem, years, phone, hour, day);
                reference.child(phone).setValue(constructor);

                Intent intent = new Intent(SignUpTwo.this, Home.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();


            }
        });


        dropdown_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = itemAdapter.getItem(i);
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