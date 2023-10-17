package com.example.mj2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mj2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class UserAccountActivity extends AppCompatActivity {

    private EditText usernameET, emailET, phoneET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        usernameET = findViewById(R.id.usernameET);
        emailET = findViewById(R.id.emailET);
        phoneET = findViewById(R.id.phoneET);

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        String name = preferences.getString("name", "sara ali");
        String email = preferences.getString("email", "sara@job.com");
        String phone = preferences.getString("phone", "01023456769");

        usernameET.setText(name);
        emailET.setText(email);
        phoneET.setText(phone);

        Firebase firebase = Firebase.INSTANCE;
    }

    @SuppressLint("ResourceAsColor")
    public void changePhoneNumber(View view) {
        phoneET.setEnabled(true);
        phoneET.setBackgroundColor(R.color.second_color);

        MaterialButton btn = findViewById(R.id.savePhoneNumber);
        view.setVisibility(View.GONE);
        btn.setVisibility(View.VISIBLE);
    }

    public void savePhoneNumber(View view) {
        phoneET.setEnabled(false);
        phoneET.setBackgroundColor(Color.WHITE);
        String phone = phoneET.getText().toString().trim();
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone", phone);
        editor.apply();

        MaterialButton btn = findViewById(R.id.changePhoneNumber);
        view.setVisibility(View.GONE);
        btn.setVisibility(View.VISIBLE);
    }

    public void deleteAccount(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(UserAccountActivity.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserAccountActivity.this, LoginActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserAccountActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}