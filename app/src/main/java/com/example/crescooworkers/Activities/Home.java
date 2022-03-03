package com.example.crescooworkers.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.crescooworkers.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {

    TextView nameTV, occupationTV, phoneTV, perDayTV, perHourTV, yearsTV;

    DatabaseReference databaseReference;
    public String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getValuesFromDB();

        Intent intent = getIntent();
         phone = intent.getStringExtra("phone");

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://crescoo-53eff-default-rtdb.firebaseio.com/");



        nameTV = findViewById(R.id.nameTV);
        occupationTV = findViewById(R.id.occupationTV);
        phoneTV = findViewById(R.id.phoneTV);
        perDayTV = findViewById(R.id.perDayTV);
        perHourTV = findViewById(R.id.perHourTV);
        yearsTV = findViewById(R.id.yearsTV);



    }

    private void getValuesFromDB() {
        databaseReference = FirebaseDatabase.getInstance().getReference("workers");
        databaseReference.child(phone).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String name = String.valueOf(dataSnapshot.child("name").getValue());
                        String occupation = String.valueOf(dataSnapshot.child("occupation").getValue());
                        String pDay = String.valueOf(dataSnapshot.child("pDay").getValue());
                        String pHour = String.valueOf(dataSnapshot.child("pHour").getValue());
                        String phone = String.valueOf(dataSnapshot.child("phone").getValue());
                        String years = String.valueOf(dataSnapshot.child("yearsOfExp").getValue());

                        nameTV.setText(name);
                        occupationTV.setText(occupation);
                        phoneTV.setText(phone);
                        perDayTV.setText(pDay);
                        perHourTV.setText(pHour);
                        yearsTV.setText(years);
                    }
                }
            }
        });
    }
}