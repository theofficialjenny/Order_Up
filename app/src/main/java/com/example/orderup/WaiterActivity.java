package com.example.orderup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class WaiterActivity extends AppCompatActivity {

    private RecyclerView recyclerOrders;
    private Button btnMarkReady, btnMarkServed;
    private OrderAdapter orderAdapter;
    private List<Order> orders; // Assume Order is a model class with ID, table, customer, items, status, etc.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);

        recyclerOrders = findViewById(R.id.recyclerOrders);
        btnMarkReady = findViewById(R.id.btn_mark_ready);
        btnMarkServed = findViewById(R.id.btn_mark_served);

        // Initialize orders (replace with your data source)
        orders = new ArrayList<>();
        // Example: orders.add(new Order("#12345", 5, "John Doe", "Mo:mo x2", "Pending", "2023-10-01 14:30"));

        orderAdapter = new OrderAdapter(orders);
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrders.setAdapter(orderAdapter);

        btnMarkReady.setOnClickListener(v -> {
            // Placeholder: Update selected order status to "Ready"
            // In a real app, get selected order from adapter and update
            // orderAdapter.updateStatus(selectedOrder, "Ready");
        });

        btnMarkServed.setOnClickListener(v -> {
            // Placeholder: Update selected order status to "Served"
            // orderAdapter.updateStatus(selectedOrder, "Served");
        });
    }
}