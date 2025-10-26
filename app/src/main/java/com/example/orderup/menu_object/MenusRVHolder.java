package com.example.orderup.menu_object;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.R;

public class MenusRVHolder extends RecyclerView.ViewHolder {
    public ImageView menuImage;
    public TextView menuName, menuDescription, menuCategory, menuPrice, menuRating, menuOrderCount;
    public Button btnAddToCart;

    public MenusRVHolder(@NonNull View itemView) {
        super(itemView);
        menuName = itemView.findViewById(R.id.menu_name);
        menuDescription = itemView.findViewById(R.id.menu_description);
        menuCategory = itemView.findViewById(R.id.menu_category);
        menuImage = itemView.findViewById(R.id.menu_image);
        menuPrice = itemView.findViewById(R.id.menu_price);
        menuRating = itemView.findViewById(R.id.menu_rating);
        menuOrderCount = itemView.findViewById(R.id.menu_count);
        btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);
    }
}