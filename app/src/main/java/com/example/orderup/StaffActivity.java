package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.menu_object.Reservation;
import com.example.orderup.menu_object.ReservationRVAdapter;
import com.example.orderup.menu_object.Status;
import com.example.orderup.menu_object.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        RecyclerView recyclerReservations = findViewById(R.id.recycler_reservations_staff);
        recyclerReservations.setLayoutManager(new LinearLayoutManager(this));

        // Sample reservations (in a real app, load from Firebase)
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation("John Doe", "Table 5", new Date(), Type.DINER, Status.PENDING));
        reservationList.add(new Reservation("Jane Smith", "Table 2", new Date(), Type.TAKEAWAY, Status.COMPLETED));

        ReservationRVAdapter adapter = new ReservationRVAdapter(reservationList);
        recyclerReservations.setAdapter(adapter);

        Button btnLogout = findViewById(R.id.btn_logout_staff);
        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(this, Login.class));
            finish();
        });
    }
}