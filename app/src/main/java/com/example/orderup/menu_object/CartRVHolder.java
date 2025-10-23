package com.example.orderup.menu_object;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

public class CartRVHolder extends RecyclerView.ViewHolder {
    public TextView itemName, itemQuantity, itemPrice;

    public CartRVHolder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.cart_item_name);
        itemQuantity = itemView.findViewById(R.id.cart_item_quantity);
        itemPrice = itemView.findViewById(R.id.cart_item_price);
    }
}