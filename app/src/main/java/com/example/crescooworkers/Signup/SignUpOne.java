package com.example.crescooworkers.Signup;

import androidx.appcompat.app.AppCompatActivity;

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

public class SignUpOne extends AppCompatActivity {
    TextInputLayout nameInputLayout, menu;
    AutoCompleteTextView dropdown_menu;
    TextView tapLogin;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_one);

        nameInputLayout = findViewById(R.id.nameInputLayout);
        menu = findViewById(R.id.menu);
        dropdown_menu = findViewById(R.id.dropdown_menu);
        tapLogin = findViewById(R.id.tapLogin);
        btnNext = findViewById(R.id.btnNext);

        //drop down menu items
        String[] items = {"Item1", "Item2", "Item69"};
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(SignUpOne.this, R.layout.drop_down_list,items);
        dropdown_menu.setAdapter(itemAdapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameInputLayout.getEditText().getText().toString();
                Toast.makeText(SignUpOne.this, name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SignUpTwo.class);
                intent.putExtra("name", name);
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

        dropdown_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 String selectedItem = itemAdapter.getItem(i);
                Toast.makeText(SignUpOne.this, selectedItem, Toast.LENGTH_SHORT).show();
            }
        });


    }
}