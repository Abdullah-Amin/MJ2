package com.example.mj2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mj2.R;
import com.example.mj2.adapters.ProductAdapter;
import com.example.mj2.data.Product;
import com.example.mj2.data.ProductsDBHelper;

import java.util.ArrayList;

public class ProductsListActivity extends AppCompatActivity {

    ArrayList<Product> products;
    private ProductsDBHelper dbHelper;

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        dbHelper = new ProductsDBHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        products = new ArrayList<>();

        getDataFromDatabase();
        recyclerView.setAdapter(new ProductAdapter(products));

    }

    private void getDataFromDatabase() {
        Cursor cursor = dbHelper.readAllData();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "There is no data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                Log.i("abdo", "getDataFromDatabase: "+ cursor.getString(1) + cursor.getString(3) + cursor.getString(4));
                Bitmap bitmap = BitmapFactory.decodeByteArray(cursor.getBlob(2), 0, cursor.getBlob(2).length);
                products.add(new Product(bitmap, cursor.getString(1), cursor.getString(3), cursor.getString(4)));
            }
        }
    }
}