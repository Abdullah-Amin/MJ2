package com.example.mj2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mj2.constants.DBConst;

public class ProductsDBHelper extends SQLiteOpenHelper {

    private Context context;
    public ProductsDBHelper(@Nullable Context context) {
        super(context, DBConst.TABLE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE " + DBConst.TABLE_NAME + " (" +
//                DBConst.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                DBConst.PRODUCT_NAME + " TEXT, " +
//                DBConst.IMAGE + " INTEGER, " +
//                DBConst.DESCRIPTION + " TEXT, " +
//                DBConst.PRICE + " INTEGER)";

        String sql = "CREATE TABLE PRODUCTS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " PRODUCT_NAME TEXT, " +
                " IMAGE INTEGER, " +
                " DESCRIPTION TEXT, " +
                " PRICE TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "PRODUCTS");
        onCreate(db);
    }

    public void addNewProduct(String productName, Integer image, String description, String price) {

        Log.i("abdo", "addNewProduct: inserting");
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("PRODUCT_NAME", productName);
        values.put(String.valueOf("IMAGE"), image);
        values.put("DESCRIPTION", description);
        values.put("PRICE", price);

        // after adding all values we are passing
        // content values to our table.
        db.insert(DBConst.TABLE_NAME, null, values);


        Log.i("abdo", "addNewProduct: inserted");

        // at last we are closing our
        // database after adding database.
//        db.close();
    }
}
