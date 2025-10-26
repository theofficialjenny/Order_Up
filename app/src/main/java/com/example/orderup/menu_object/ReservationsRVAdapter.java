package com.example.orderup.menu_object;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

import java.util.List;

public class ReservationsRVAdapter extends RecyclerView.Adapter<ReservationsRVHolder> {
    private List<Reservation> reservationList;

    public ReservationsRVAdapter(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @NonNull
    @Override
    public ReservationsRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation, parent, false);
        return new ReservationsRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationsRVHolder holder, int position) {
        Reservation reservation = reservationList.get(position);

        holder.reservationUser.setText(reservation.getUser());
        holder.reservationTableNumber.setText(reservation.getTableNumber());
        holder.reservationDate.setText(reservation.getDate().toString());
        holder.reservationType.setText(reservation.getType().toString());
        holder.reservationStatus.setText(reservation.getStatus().toString());
    }

    @Override
    public int getItemCount()  {
        return reservationList != null ? reservationList.size() : 0;
    }

    public void updateData(List<Reservation> reservationList) {
        this.reservationList = reservationList;
        notifyDataSetChanged();
    }

    public void filterList(List<Reservation> filteredList) {
        this.reservationList = filteredList;
        notifyDataSetChanged();
    }
}
