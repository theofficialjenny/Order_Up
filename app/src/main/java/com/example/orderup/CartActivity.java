package com.example.orderup;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.menu_object.CartRVAdapter;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerCart = findViewById(R.id.recycler_cart);
        totalPrice = findViewById(R.id.total_price);
        Button btnCheckout = findViewById(R.id.btn_checkout);

        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        CartRVAdapter adapter = new CartRVAdapter(CartManager.getInstance().getCartItems());
        recyclerCart.setAdapter(adapter);

        updateTotal();

        btnCheckout.setOnClickListener(v -> {
            // Implement checkout logic (e.g., payment integration)
            Toast.makeText(this, "Checkout not implemented", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateTotal() {
        double total = CartManager.getInstance().getCartItems().stream()
                .mapToDouble(Cart::getTotalPrice).sum();
        totalPrice.setText("Total: $" + String.format("%.2f", total));
    }
}