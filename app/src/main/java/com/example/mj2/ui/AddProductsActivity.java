package com.example.mj2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mj2.R;
import com.example.mj2.data.ProductsDBHelper;

public class AddProductsActivity extends AppCompatActivity {

    private ProductsDBHelper productsDBHelper;

    private EditText nameET, descriptionET, priceET;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        nameET = findViewById(R.id.nameET);
        descriptionET = findViewById(R.id.descriptionET);
        priceET = findViewById(R.id.priceET);


        productsDBHelper = new ProductsDBHelper(this);

        for (int i = 0; i < 10; i++) {
        }
    }

    public void add(View view) {
        String name = nameET.getText().toString().trim();
        String description = descriptionET.getText().toString().trim();
        String price = priceET.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || price.isEmpty()){
            Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        productsDBHelper.addNewProduct(name, R.drawable.meat_image, description, price);
    }
}