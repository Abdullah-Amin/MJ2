package com.example.mj2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mj2.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToUserAccount(View view) {
        startActivity(new Intent(this, UserAccountActivity.class));
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}