package com.example.orderup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserProfileActivity extends AppCompatActivity {

    private TextView tvEmail, tvPassword;
    private RecyclerView recyclerOrders, recyclerBookings;
    private FirebaseFirestore db;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvEmail = findViewById(R.id.tv_email);
        tvPassword = findViewById(R.id.tv_password);
        recyclerOrders = findViewById(R.id.recycler_orders);
        recyclerBookings = findViewById(R.id.recycler_bookings);

        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        recyclerBookings.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Toast.makeText(this, "No user logged in!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        tvEmail.setText("Email: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        tvPassword.setText("Password: Encrypted (not shown for security)");

        loadUserOrders();
        loadUserBookings();
    }

    private void loadUserOrders() {
        db.collection("orders").whereEqualTo("customerId", userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<String> orders = new ArrayList<>();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    String orderInfo = "Table: " + doc.getString("tableNumber")
                            + "\nItems: " + doc.getString("items")
                            + "\nStatus: " + doc.getString("status");
                    orders.add(orderInfo);
                }
                recyclerOrders.setAdapter(new SimpleTextAdapter(orders));
            } else {
                Toast.makeText(this, "Failed to load orders.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserBookings() {
        db.collection("reservations").whereEqualTo("customerId", userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<String> bookings = new ArrayList<>();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    String bookingInfo = "Table: " + doc.getString("tableNumber")
                            + "\nDate: " + doc.getString("date")
                            + "\nTime: " + doc.getString("time")
                            + "\nPeople: " + doc.getString("people");
                    bookings.add(bookingInfo);
                }
                recyclerBookings.setAdapter(new SimpleTextAdapter(bookings));
            } else {
                Toast.makeText(this, "Failed to load bookings.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ✅ Simple text adapter
    private static class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {
        private final List<String> items;

        public SimpleTextAdapter(List<String> items) {
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            textView.setPadding(24, 24, 24, 24);
            return new ViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            ViewHolder(TextView textView) {
                super(textView);
                this.textView = textView;
            }
        }
    }

    // ✅ Bottom navigation button handlers
    public void openHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void openSearchActivity(View view) {
        startActivity(new Intent(this, Search.class));
    }

    public void openCartActivity(View view) {
        startActivity(new Intent(this, CartActivity.class));
    }
}
