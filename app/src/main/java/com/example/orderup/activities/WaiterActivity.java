package com.example.orderup.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;  // Added for toasts
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.R;
import com.example.orderup.menu_object.Table;
import com.example.orderup.menu_object.TablesRVAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;  // Added
import java.util.List;
import java.util.Map;  // Added

public class WaiterActivity extends AppCompatActivity {

    private RecyclerView recyclerTables;
    private Button btnMarkServed;
    private TablesRVAdapter tablesRVAdapter;
    private List<Table> tables;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);

        recyclerTables = findViewById(R.id.recyclerOrders);
        btnMarkServed = findViewById(R.id.btn_mark_served);

        tables = new ArrayList<>();
        tablesRVAdapter = new TablesRVAdapter(tables);
        recyclerTables.setLayoutManager(new LinearLayoutManager(this));
        recyclerTables.setAdapter(tablesRVAdapter);

        db = FirebaseFirestore.getInstance();

        // *** SEED INITIAL TABLES DATA (RUN ONCE, THEN REMOVE) ***
        seedTablesData();  // Call the seeding method

        loadTables();

        btnMarkServed.setOnClickListener(v -> {
            // Placeholder: Mark selected table as served
            // In a real app, get selected table from adapter and update
            // e.g., tablesRVAdapter.markServed(selectedPosition);
        });
    }

    // *** NEW METHOD: Seed initial tables data ***
    private void seedTablesData() {
        // Table 1: Available
        Map<String, Object> table1 = new HashMap<>();
        table1.put("tableNumber", 1);
        table1.put("status", "Available");
        table1.put("capacity", 4);
        table1.put("items", "");
        table1.put("price", 0.0);
        table1.put("time", "");
        table1.put("customerName", "");
        table1.put("reservationId", "");

        db.collection("tables").document("table1").set(table1)
                .addOnSuccessListener(aVoid -> Toast.makeText(WaiterActivity.this, "Table 1 added!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(WaiterActivity.this, "Failed to add Table 1: " + e.getMessage(), Toast.LENGTH_SHORT).show());

        // Table 2: Pending (with order)
        Map<String, Object> table2 = new HashMap<>();
        table2.put("tableNumber", 2);
        table2.put("status", "Pending");
        table2.put("capacity", 2);
        table2.put("items", "Mo:mo x2, Pizza x1");
        table2.put("price", 35.50);
        table2.put("time", "14:30");
        table2.put("customerName", "John Doe");
        table2.put("reservationId", "");

        db.collection("tables").document("table2").set(table2)
                .addOnSuccessListener(aVoid -> Toast.makeText(WaiterActivity.this, "Table 2 added!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(WaiterActivity.this, "Failed to add Table 2: " + e.getMessage(), Toast.LENGTH_SHORT).show());

        // Table 3: Served
        Map<String, Object> table3 = new HashMap<>();
        table3.put("tableNumber", 3);
        table3.put("status", "Served");
        table3.put("capacity", 6);
        table3.put("items", "Burger x2, Fries x1");
        table3.put("price", 25.99);
        table3.put("time", "13:45");
        table3.put("customerName", "Jane Smith");
        table3.put("reservationId", "");

        db.collection("tables").document("table3").set(table3)
                .addOnSuccessListener(aVoid -> Toast.makeText(WaiterActivity.this, "Table 3 added!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(WaiterActivity.this, "Failed to add Table 3: " + e.getMessage(), Toast.LENGTH_SHORT).show());

        // Table 4: Available
        Map<String, Object> table4 = new HashMap<>();
        table4.put("tableNumber", 4);
        table4.put("status", "Available");
        table4.put("capacity", 4);
        table4.put("items", "");
        table4.put("price", 0.0);
        table4.put("time", "");
        table4.put("customerName", "");
        table4.put("reservationId", "");

        db.collection("tables").document("table4").set(table4)
                .addOnSuccessListener(aVoid -> Toast.makeText(WaiterActivity.this, "Table 4 added!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(WaiterActivity.this, "Failed to add Table 4: " + e.getMessage(), Toast.LENGTH_SHORT).show());

        // Table 5: Pending
        Map<String, Object> table5 = new HashMap<>();
        table5.put("tableNumber", 5);
        table5.put("status", "Pending");
        table5.put("capacity", 3);
        table5.put("items", "Salad x1");
        table5.put("price", 15.50);
        table5.put("time", "15:00");
        table5.put("customerName", "Bob Johnson");
        table5.put("reservationId", "");

        db.collection("tables").document("table5").set(table5)
                .addOnSuccessListener(aVoid -> Toast.makeText(WaiterActivity.this, "Table 5 added!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(WaiterActivity.this, "Failed to add Table 5: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void loadTables() {
        db.collection("tables").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                tables.clear();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Table table = doc.toObject(Table.class);
                    tables.add(table);
                }
                tablesRVAdapter.notifyDataSetChanged();
            }
        });
    }
}