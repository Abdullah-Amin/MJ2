package com.example.mj2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mj2.R;
import com.example.mj2.callbacks.ProductsI;
import com.example.mj2.data.Product;

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
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
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
        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.name);
            descriptionTV = itemView.findViewById(R.id.description);
            priceTV = itemView.findViewById(R.id.price);
        }
    }
}
