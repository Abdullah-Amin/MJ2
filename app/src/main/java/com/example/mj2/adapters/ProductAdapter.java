package com.example.mj2.adapters;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mj2.R;
import com.example.mj2.callbacks.ProductsI;
import com.example.mj2.data.Product;

import java.io.IOException;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private ArrayList<Product> products;
    private ProductsI productsI;

    public ProductAdapter(ArrayList<Product> products, ProductsI productsI) {
        this.products = products;
        this.productsI = productsI;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, @SuppressLint("RecyclerView") int position) {

        byte[] bt = products.get(position).getProductImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(products.get(position).getProductImage(), 0, bt.length);
        holder.image.setImageBitmap(bitmap);

        holder.nameTV.setText(products.get(position).getProductName());
        holder.descriptionTV.setText(products.get(position).getProductDescription());
        holder.priceTV.setText(products.get(position).getProductPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productsI.getProduct(products.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder {

        TextView nameTV, descriptionTV, priceTV;
        ImageView image;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            nameTV = itemView.findViewById(R.id.name);
            descriptionTV = itemView.findViewById(R.id.description);
            priceTV = itemView.findViewById(R.id.price);
        }
    }
}
