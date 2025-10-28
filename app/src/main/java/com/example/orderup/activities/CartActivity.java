package com.example.orderup.activities;  // Assuming this is your package; adjust if needed

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;

import com.example.orderup.menu_object.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.orderup.menu_object.Order;
import com.google.firebase.Timestamp;
import java.util.UUID;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.menu_object.CartItem;  // Added import for CartItem
import com.example.orderup.R;
import com.example.orderup.menu_object.CartRVAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerCart;
    private TextView tvTotalPrice;
    private Button btnCheckout;
    private CartRVAdapter cartRVAdapter;  // Ensure this matches your adapter name
    private List<CartItem> cartItems;
    private FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db = FirebaseFirestore.getInstance();

        recyclerCart = findViewById(R.id.recyclerCart);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        btnCheckout = findViewById(R.id.btn_checkout);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String cartJson = prefs.getString("cart_items", "[]");
        cartItems = gson.fromJson(cartJson, new TypeToken<List<CartItem>>() {
        }.getType());

        cartRVAdapter = new CartRVAdapter(cartItems, this::updateTotalPrice);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(cartRVAdapter);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        updateTotalPrice();

        btnCheckout.setOnClickListener(v -> showCheckoutConfirmation());
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

    private void showCheckoutConfirmation() {
        if (cartItems.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Empty Cart")
                    .setMessage("Your cart is empty. Add items before checking out.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Confirm Order")
                .setMessage("Are you sure you want to place this order?")
                .setPositiveButton("Yes", (dialog, which) -> createOrder())
                .setNegativeButton("No", null)
                .show();
    }

    private void createOrder() {
        // Calculate total price
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        // Create order object
        Order order = new Order(
                user.getUid(),
                cartItems.toString(),
                Status.Pending,
                total,
                Timestamp.now().toString()
        );

        // Save to Firestore
        String orderId = UUID.randomUUID().toString();
        db.collection("orders")
                .document(orderId)
                .set(order)
                .addOnSuccessListener(aVoid -> {
                    // Clear cart on success
                    cartItems.clear();
                    saveCartToPrefs();
                    cartRVAdapter.notifyDataSetChanged();
                    updateTotalPrice();

                    // Show success message
                    new AlertDialog.Builder(this)
                            .setTitle("Order Placed")
                            .setMessage("Your order has been placed successfully!")
                            .setPositiveButton("OK", null)
                            .show();
                })
                .addOnFailureListener(e -> {
                    // Show error message
                    new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Failed to place order. Please try again.")
                            .setPositiveButton("OK", null)
                            .show();
                });
    }

    private void saveCartToPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString("cart_items", json);
        editor.apply();
    }
}