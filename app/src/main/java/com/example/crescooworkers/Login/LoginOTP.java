package com.example.crescooworkers.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crescooworkers.Activities.Profiles;
import com.example.crescooworkers.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import java.util.concurrent.TimeUnit;

public class LoginOTP extends AppCompatActivity {

    Button btnNext;
    OtpView otpView;
    TextView resendOTP;

    FirebaseAuth auth;
    DatabaseReference databaseReference;

    public String uPhone;
    String verificationID;
    String tag = "LoginOTP";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);

        btnNext = findViewById(R.id.btnNext);
        otpView = findViewById(R.id.otp_view);
        resendOTP = findViewById(R.id.resendOTP);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://crescoo-53eff-default-rtdb.firebaseio.com/");


        Intent intent = getIntent();
        uPhone = intent.getStringExtra("phoneNumber");

        sendOTP();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
                    @Override
                    public void onOtpCompleted(String otp) {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, otp);

                        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(LoginOTP.this, Profiles.class);
                                    intent.putExtra("phone", uPhone);
                                    startActivity(intent);
                                    finishAffinity();
                                }else {
                                    Toast.makeText(LoginOTP.this, "Failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });

            }
        });

        //resend OTP
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(uPhone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(LoginOTP.this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                            }

                            @Override
                            public void onCodeSent(@NonNull String newOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newOTP, forceResendingToken);
                                verificationID = newOTP;
                                Toast.makeText(LoginOTP.this, "Resent", Toast.LENGTH_SHORT).show();
                            }
                        }).build();

                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });


    }




    private void sendOTP() {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(uPhone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(LoginOTP.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String verifyID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verifyID, forceResendingToken);
                        verificationID = verifyID;
                    }
                }).build();

        PhoneAuthProvider.verifyPhoneNumber(options);

        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, otp);

                auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(LoginOTP.this, Profiles.class);
                            intent.putExtra("phone", uPhone);
                            startActivity(intent);
                            finishAffinity();
                        }else {
                            Toast.makeText(LoginOTP.this, "Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
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