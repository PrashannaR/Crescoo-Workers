package com.example.crescooworkers.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.crescooworkers.R;
import com.example.crescooworkers.Signup.SignUpOne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {

    TextView nameTV, occupationTV, phoneTV, perDayTV, perHourTV, yearsTV;
    Button btnLogout;

    DatabaseReference databaseReference;
    public String phone;
    String tag = "Home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://crescoo-53eff-default-rtdb.firebaseio.com/");

        btnLogout = findViewById(R.id.btnLogout);

        nameTV = findViewById(R.id.nameTV);
        occupationTV = findViewById(R.id.occupationTV);
        phoneTV = findViewById(R.id.phoneTV);
        perDayTV = findViewById(R.id.perDayTV);
        perHourTV = findViewById(R.id.perHourTV);
        yearsTV = findViewById(R.id.yearsTV);

        getValuesFromDB();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent1 = new Intent(Home.this, SignUpOne.class);
                startActivity(intent1);
                finish();
            }
        });

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