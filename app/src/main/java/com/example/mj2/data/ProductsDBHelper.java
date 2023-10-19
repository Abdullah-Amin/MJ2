package com.example.mj2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mj2.constants.DBConst;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProductsDBHelper extends SQLiteOpenHelper {

    private Context context;
    public ProductsDBHelper(@Nullable Context context) {
        super(context, DBConst.TABLE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE PRODUCTS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " PRODUCT_NAME TEXT, " +
                " IMAGE BLOB, " +
                " DESCRIPTION TEXT, " +
                " PRICE TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "PRODUCTS");
        onCreate(db);
    }

    public void addNewProduct(String productName, Bitmap image, String description, String price) throws IOException {

        Log.i("abdo", "addNewProduct: inserting");
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, imageStream);
        byte[] imageInByte = imageStream.toByteArray();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("PRODUCT_NAME", productName);
        values.put("IMAGE", imageInByte);
        values.put("DESCRIPTION", description);
        values.put("PRICE", price);

        // after adding all values we are passing
        // content values to our table.
        db.insert(DBConst.TABLE_NAME, null, values);
        Toast.makeText(context, "Product added successfully", Toast.LENGTH_SHORT).show();

        Log.i("abdo", "addNewProduct: inserted");

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM PRODUCTS";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
