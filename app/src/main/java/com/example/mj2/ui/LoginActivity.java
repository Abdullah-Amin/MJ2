package com.example.mj2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mj2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText emailET, passwordET;

    private int counter = 0;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
    }

    public void login(View view) {
        counter++;
        if (counter == 2) {
            view.setEnabled(false);
            MaterialButton btn = findViewById(R.id.loginBtn);
            btn.setTextColor(Color.GRAY);
            Toast.makeText(this, "Login is disabled", Toast.LENGTH_SHORT).show();
        }
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();


        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.i(TAG, "abdo: success");
                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void goToRegisterPage(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}