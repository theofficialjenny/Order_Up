package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerCart;
    private TextView tvTotalPrice;
    private Button btnCheckout;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems; // Assume CartItem is a model class with name, price, quantity, etc.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerCart = findViewById(R.id.recyclerCart);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        btnCheckout = findViewById(R.id.btn_checkout);

        // Initialize cart items (replace with your data source, e.g., from a database or shared preferences)
        cartItems = new ArrayList<>();
        // Example: cartItems.add(new CartItem("Mo:mo", "Tasty dumplings", 5.00, 2));

        cartAdapter = new CartAdapter(cartItems, this::updateTotalPrice);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(cartAdapter);

        updateTotalPrice();

        btnCheckout.setOnClickListener(v -> {
            // Placeholder: Implement checkout logic (e.g., payment integration)
            // For now, just show a toast or navigate to a payment activity
            // Toast.makeText(this, "Checkout initiated", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateTotalPrice() {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        tvTotalPrice.setText("Total: $" + String.format("%.2f", total));
    }

    // Navigation methods (add these to match your XML onClick)
    public void openHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openSearchActivity(View view) {
        Intent intent = new Intent(this, Search.class); // Assume SearchActivity exists
        startActivity(intent);
    }
}