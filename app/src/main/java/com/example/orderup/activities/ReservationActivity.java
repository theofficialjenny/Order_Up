package com.example.orderup.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;  // Added
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.R;
import com.example.orderup.menu_object.Table;
import com.example.orderup.menu_object.TablesRVAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationActivity extends AppCompatActivity {

    private Spinner spinnerPeople;
    private RecyclerView recyclerTables;
    private LinearLayout layoutForm, layoutSummary;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText etConfirmPeople;  // Added
    private TextView tvSummary;
    private Button btnNext, btnReserve, btnBack;
    private TablesRVAdapter tablesAdapter;
    private List<Table> availableTables;
    private Table selectedTable;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        spinnerPeople = findViewById(R.id.spinner_people);
        recyclerTables = findViewById(R.id.recycler_tables);
        layoutForm = findViewById(R.id.layout_form);
        layoutSummary = findViewById(R.id.layout_summary);
        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        etConfirmPeople = findViewById(R.id.et_confirm_people);  // Added
        tvSummary = findViewById(R.id.tv_summary);
        btnNext = findViewById(R.id.btn_next);
        btnReserve = findViewById(R.id.btn_reserve);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        availableTables = new ArrayList<>();
        tablesAdapter = new TablesRVAdapter(availableTables);
        recyclerTables.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerTables.setAdapter(tablesAdapter);

        db = FirebaseFirestore.getInstance();
        loadAvailableTables();

        // Enforce 15-min intervals on TimePicker
        timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            int roundedMinute = (minute / 15) * 15;  // Round to nearest 15-min
            if (roundedMinute != minute) {
                timePicker.setMinute(roundedMinute);
            }
        });

        findViewById(R.id.btn_select_people).setOnClickListener(v -> {
            recyclerTables.setVisibility(View.VISIBLE);
        });

        tablesAdapter.setOnTableClickListener(table -> {
            selectedTable = table;
            layoutForm.setVisibility(View.VISIBLE);
            recyclerTables.setVisibility(View.GONE);

            // Hide the spinner and select button
            spinnerPeople.setVisibility(View.GONE);
            findViewById(R.id.btn_select_people).setVisibility(View.GONE);
        });

        btnNext.setOnClickListener(v -> {
            // Get selected date
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;  // Month is 0-based
            int year = datePicker.getYear();
            String date = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);

            // Get selected time
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();
            String time = String.format("%02d:%02d", hour, minute);

            // Get people from confirm field (instead of spinner)
            int people = Integer.parseInt(etConfirmPeople.getText().toString().trim());

            tvSummary.setText("Table: " + selectedTable.getTableNumber() + "\nDate: " + date + "\nTime: " + time + "\nPeople: " + people);
            layoutSummary.setVisibility(View.VISIBLE);
            layoutForm.setVisibility(View.GONE);
        });

        btnReserve.setOnClickListener(v -> {
            // Reuse the same date/time/people logic from btnNext
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();
            String date = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);

            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();
            String time = String.format("%02d:%02d", hour, minute);

            // Get people from confirm field
            int people = Integer.parseInt(etConfirmPeople.getText().toString().trim());

            Map<String, Object> reservationData = new HashMap<>();
            reservationData.put("tableNumber", selectedTable.getTableNumber());
            reservationData.put("date", date);
            reservationData.put("time", time);
            reservationData.put("people", people);
            reservationData.put("customerId", FirebaseAuth.getInstance().getCurrentUser().getUid());
            reservationData.put("status", "Confirmed");
            reservationData.put("timestamp", FieldValue.serverTimestamp());

            db.collection("reservations").add(reservationData)
                    .addOnSuccessListener(docRef -> {
                        db.collection("tables").document("table" + selectedTable.getTableNumber()).update("status", "Pending");
                        Toast.makeText(this, "Reservation saved!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Failed to save reservation: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }

    private void loadAvailableTables() {
        db.collection("tables").whereEqualTo("status", "Available").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                availableTables.clear();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Table table = doc.toObject(Table.class);
                    availableTables.add(table);
                }
                tablesAdapter.notifyDataSetChanged();
            }
        });
    }
}
