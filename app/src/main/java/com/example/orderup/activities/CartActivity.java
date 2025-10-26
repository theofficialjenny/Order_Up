package com.example.orderup.activities;  // Assuming this is your package; adjust if needed

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;  // Added
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.menu_object.CartItem;  // Added import for CartItem
import com.example.orderup.R;
import com.example.orderup.menu_object.CartRVAdapter;  // Added import for CartRVAdapter
import com.google.gson.Gson;  // Added
import com.google.gson.reflect.TypeToken;  // Added
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerCart;
    private TextView tvTotalPrice;
    private Button btnCheckout;
    private CartRVAdapter cartRVAdapter;  // Ensure this matches your adapter name
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerCart = findViewById(R.id.recyclerCart);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        btnCheckout = findViewById(R.id.btn_checkout);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String cartJson = prefs.getString("cart_items", "[]");
        cartItems = gson.fromJson(cartJson, new TypeToken<List<CartItem>>(){}.getType());

        cartRVAdapter = new CartRVAdapter(cartItems, this::updateTotalPrice);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(cartRVAdapter);

        updateTotalPrice();

        btnCheckout.setOnClickListener(v -> {
            // Placeholder for checkout
        });
    }

    private void updateTotalPrice() {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        tvTotalPrice.setText("Total: $" + String.format("%.2f", total));
    }

    public void openHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void openSearchActivity(View view) {
        startActivity(new Intent(this, Search.class));
    }
}