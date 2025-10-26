package com.example.orderup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ReservationsFragment extends Fragment {

    private RecyclerView recyclerReservations;
    private ReservationAdapter reservationAdapter; // Assume you have this adapter
    private List<Reservation> reservations; // Assume Reservation model class

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservations, container, false); // Simple layout with RecyclerView
        recyclerReservations = view.findViewById(R.id.recyclerReservations);
        recyclerReservations.setLayoutManager(new LinearLayoutManager(getContext()));

        reservations = new ArrayList<>();
        // Load reservations from database
        reservationAdapter = new ReservationAdapter(reservations);
        recyclerReservations.setAdapter(reservationAdapter);

        return view;
    }
}