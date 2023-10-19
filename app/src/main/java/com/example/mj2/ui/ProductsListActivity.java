package com.example.mj2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;

import com.example.mj2.R;
import com.example.mj2.constants.DBConst;
import com.example.mj2.data.ProductsDBHelper;

public class ProductsListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

    }
}