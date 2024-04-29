package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText email,password;
    private Button btnSignUp;
    private TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        login = findViewById(R.id.btnRedirectText);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = email.getText().toString().trim();
                String pass = password.getText().toString().trim();


                if(username.isEmpty()){
                    email.setError("Email cannot be empty");
                }

                if(pass.isEmpty()){
                    password.setError("Password cannot be empty");
                } else{
                    auth.createUserWithEmailAndPassword(username,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Register Succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Loginpage.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Register Not Succes" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, Loginpage.class));
                    }
                });
            }
        });

    }
}