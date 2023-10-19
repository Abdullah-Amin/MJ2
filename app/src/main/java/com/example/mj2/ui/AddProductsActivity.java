package com.example.mj2.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mj2.R;
import com.example.mj2.data.ProductsDBHelper;

import java.io.IOException;


public class AddProductsActivity extends AppCompatActivity {

    private ProductsDBHelper productsDBHelper;

    private EditText nameET, descriptionET, priceET;
    private ImageView imageView;

    private Bitmap bitmap;

    ActivityResultLauncher<Intent> someActivityResultLauncher = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        nameET = findViewById(R.id.nameET);
        descriptionET = findViewById(R.id.descriptionET);
        priceET = findViewById(R.id.priceET);
        imageView = findViewById(R.id.productImage);

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Log.i("abdo", "onActivityResult: " + result.getData().getData());
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(AddProductsActivity.this.getContentResolver(), result.getData().getData());
                                imageView.setImageBitmap(bitmap);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Log.i("abdo", "onActivityResult: path: " + bitmap.toString());
                        }
                    }
                });

        productsDBHelper = new ProductsDBHelper(this);

        for (int i = 0; i < 10; i++) {
        }
    }

    public void add(View view) throws IOException {
        String name = nameET.getText().toString().trim();
        String description = descriptionET.getText().toString().trim();
        String price = priceET.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || price.isEmpty()){
            Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.i("abdo", "add: " + bitmap.toString()) ;

        productsDBHelper.addNewProduct(name, bitmap, description, price);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void uploadImage(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermission();
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);
    }

    private void requestPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            someActivityResultLauncher.launch(intent);

        } else {
            Toast.makeText(this, "Required a photo to continue !!", Toast.LENGTH_SHORT).show();
        }
    }
}