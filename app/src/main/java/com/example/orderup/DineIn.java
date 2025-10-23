package com.example.orderup;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.menu_object.Reservation;
import com.example.orderup.menu_object.ReservationRVAdapter;
import com.example.orderup.menu_object.Status;
import com.example.orderup.menu_object.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DineIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dine_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView for reservations
        RecyclerView recyclerReservations = findViewById(R.id.recycler_reservations);
        recyclerReservations.setLayoutManager(new LinearLayoutManager(this));

        // Sample data for reservations
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation("John Doe", "Table 5", new Date(), Type.DINER, Status.PENDING));
        reservationList.add(new Reservation("Jane Smith", "Table 2", new Date(), Type.TAKEAWAY, Status.COMPLETED));
        reservationList.add(new Reservation("Alice Johnson", "Table 10", new Date(), Type.DINER, Status.CANCELLED));

        // Set adapter to bind data to RecyclerView
        ReservationRVAdapter adapter = new ReservationRVAdapter(reservationList);
        recyclerReservations.setAdapter(adapter);
    }
}