package com.example.orderup.menu_object;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

public class MenuRVHolder extends RecyclerView.ViewHolder {
    public ImageView menuImage;
    public TextView menuName, menuRating, menuOrders, menuDescription, menuTag, menuCategory, menuPrice;
    public Button buttonAddToCart;

    public MenuRVHolder(@NonNull View itemView) {
        super(itemView);
        menuImage = itemView.findViewById(R.id.menu_image);
        menuName = itemView.findViewById(R.id.menu_name);
        menuRating = itemView.findViewById(R.id.menu_rating);
        menuOrders = itemView.findViewById(R.id.menu_orders);
        menuDescription = itemView.findViewById(R.id.menu_description);
        menuTag = itemView.findViewById(R.id.menu_tag);
        menuCategory = itemView.findViewById(R.id.menu_category);  // NEW
        menuPrice = itemView.findViewById(R.id.menu_price);        // NEW
        buttonAddToCart = itemView.findViewById(R.id.button_add_to_cart);
    }
}