package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Loginpage extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText logemail,logpassword;
    private Button btnSignIn;
    private TextView reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);


        auth = FirebaseAuth.getInstance();
        logemail = findViewById(R.id.logEmail);
        logpassword = findViewById(R.id.logPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        reg = findViewById(R.id.btnLoginText);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logEmail = logemail.getText().toString().trim();
                String logPass = logpassword.getText().toString().trim();

                if(!logEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(logEmail).matches()){
                    if(!logPass.isEmpty()){
                        auth.signInWithEmailAndPassword(logEmail,logPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Loginpage.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Loginpage.this,Dashboard.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Loginpage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        logpassword.setError("Password cannot be empty");
                    }
                } else if(logEmail.isEmpty()){
                    logemail.setError("Email cannot be empty");
                } else {
                    logemail.setError("Invalid Email");
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginpage.this,MainActivity.class));
            }
        });

    }
}