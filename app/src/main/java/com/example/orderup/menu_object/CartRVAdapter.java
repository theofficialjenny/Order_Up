package com.example.orderup.menu_object;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

import java.util.List;

public class CartRVAdapter extends RecyclerView.Adapter<CartRVHolder> {

    private List<CartItem> cartItems;
    private Runnable updateTotalCallback; // Callback to update total price in activity

    public CartRVAdapter(List<CartItem> cartItems, Runnable updateTotalCallback) {
        this.cartItems = cartItems;
        this.updateTotalCallback = updateTotalCallback;
    }

    @NonNull
    @Override
    public CartRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRVHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemPrice.setText("Unit Price: $" + String.format("%.2f", item.getPrice()));
        holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
        holder.itemTotal.setText("Total: $" + String.format("%.2f", item.getPrice() * item.getQuantity()));
        holder.itemImage.setImageResource(item.getImageResId()); // Or use Glide/Picasso for URLs

        holder.btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
                updateTotalCallback.run();
            }
        });

        holder.btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            updateTotalCallback.run();
        });

        holder.btnRemove.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            updateTotalCallback.run();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}