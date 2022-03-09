package com.example.crescooworkers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.crescooworkers.R;
import com.example.crescooworkers.Signup.SignUpTwo;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
    TextInputLayout nameInputLayout, genderInputLayout, ageInputLayout, yearsInputLayout, menu, hourInputLayout, dayInputLayout;
    AutoCompleteTextView dropdown_menu;
    Button btnUpdate;

    DatabaseReference reference;

    public String years, hour, day, selectedItem, name, phone, gender, age;
    public String uName, uHour, uDay, uOcc, uYear, uGender, uAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nameInputLayout = findViewById(R.id.nameInputLayout);
        genderInputLayout = findViewById(R.id.genderInputLayout);
        ageInputLayout = findViewById(R.id.ageInputLayout);
        yearsInputLayout = findViewById(R.id.yearsInputLayout);
        menu = findViewById(R.id.menu);
        hourInputLayout = findViewById(R.id.hourInputLayout);
        dayInputLayout = findViewById(R.id.dayInputLayout);

        dropdown_menu = findViewById(R.id.dropdown_menu);

        btnUpdate = findViewById(R.id.btnUpdate);

        reference = FirebaseDatabase.getInstance().getReference("workers");

        //get Value
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        uName = intent.getStringExtra("name");
        uAge = intent.getStringExtra("age");
        uDay = intent.getStringExtra("perDay");
        uGender = intent.getStringExtra("gender");
        uHour = intent.getStringExtra("perHour");
        uOcc = intent.getStringExtra("occupation");
        uYear = intent.getStringExtra("years");

        //set Values to the Edit texts
        nameInputLayout.getEditText().setText(uName);
        ageInputLayout.getEditText().setText(uAge);
        dayInputLayout.getEditText().setText(uDay);
        genderInputLayout.getEditText().setText(uGender);
        hourInputLayout.getEditText().setText(uHour);
        menu.getEditText().setText(uOcc);
        yearsInputLayout.getEditText().setText(uYear);
        selectedItem = uOcc;

        //drop down menu items
        String[] items = {"Carpentry", "Cleaning", "Blacksmith", "Plumbing", "Electrical", "Interiors", "Movers"};
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(EditProfile.this, R.layout.drop_down_list,items);
        dropdown_menu.setAdapter(itemAdapter);
        dropdown_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = itemAdapter.getItem(i);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input validations
                if(!checkName() || !checkGender() || !checkAge() || !checkYears()||!checkHour()||!checkDay()){
                    return;
                }

                //get Values from EditTexts
                years = yearsInputLayout.getEditText().getText().toString();
                hour = hourInputLayout.getEditText().getText().toString();
                day = dayInputLayout.getEditText().getText().toString();
                name = nameInputLayout.getEditText().getText().toString();
                gender = genderInputLayout.getEditText().getText().toString();
                age = ageInputLayout.getEditText().getText().toString();

               //Toast.makeText(EditProfile.this, years + "\n" + hour + "\n"+ day + "\n"+ name + "\n"+ gender + "\n" + age + "\n", Toast.LENGTH_SHORT).show();
                update();
                Toast.makeText(EditProfile.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditProfile.this, Profiles.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });


    }

    private void update() {

        years = yearsInputLayout.getEditText().getText().toString();
        hour = hourInputLayout.getEditText().getText().toString();
        day = dayInputLayout.getEditText().getText().toString();
        name = nameInputLayout.getEditText().getText().toString();
        gender = genderInputLayout.getEditText().getText().toString();
        age = ageInputLayout.getEditText().getText().toString();

        reference.child(phone).child("name").setValue(name);
        reference.child(phone).child("yearsOfExp").setValue(years);
        reference.child(phone).child("age").setValue(age);
        reference.child(phone).child("gender").setValue(gender);
        reference.child(phone).child("occupation").setValue(selectedItem);
        reference.child(phone).child("pDay").setValue(day);
        reference.child(phone).child("pHour").setValue(hour);
    }


    //input validations
    private boolean checkYears() {
        years = yearsInputLayout.getEditText().getText().toString();

        if(years.isEmpty()){
            yearsInputLayout.setError("Enter Your Years Of Experience");
            return false;
        }else {
            yearsInputLayout.setError(null);
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

}