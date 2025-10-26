package com.example.orderup.menu_object;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

public class CartRVHolder extends RecyclerView.ViewHolder {
    ImageView itemImage;
    TextView itemName, itemDescription, itemPrice, itemQuantity, itemTotal;
    Button btnDecrease, btnIncrease, btnRemove;

    public CartRVHolder(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.cart_item_image);
        itemName = itemView.findViewById(R.id.cart_item_name);
        itemDescription = itemView.findViewById(R.id.cart_item_description);
        itemPrice = itemView.findViewById(R.id.cart_item_price);
        itemQuantity = itemView.findViewById(R.id.cart_item_quantity);
        itemTotal = itemView.findViewById(R.id.cart_item_total);
        btnDecrease = itemView.findViewById(R.id.btn_decrease_quantity);
        btnIncrease = itemView.findViewById(R.id.btn_increase_quantity);
        btnRemove = itemView.findViewById(R.id.btn_remove_item);
    }
}