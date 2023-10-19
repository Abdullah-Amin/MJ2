package com.example.mj2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mj2.R;
import com.example.mj2.data.Product;

public class ProductsActivity extends AppCompatActivity {

    private Product product;

    private TextView nameTV, descriptionTV, priceTV;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        nameTV = findViewById(R.id.name);
        descriptionTV = findViewById(R.id.description);
        priceTV = findViewById(R.id.price);

        product = (Product) getIntent().getSerializableExtra("product");

        nameTV.setText(product.getProductName());
        descriptionTV.setText(product.getProductDescription());
        priceTV.setText(product.getProductPrice());
    }
}