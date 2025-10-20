package com.example.orderup.menu_object;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

import java.util.List;

public class ReservationRVAdapter extends RecyclerView.Adapter<ReservationRVHolder> {
    private List<Reservation> reservationList;

    public ReservationRVAdapter(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @NonNull
    @Override
    public ReservationRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation, parent, false);
        return new ReservationRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationRVHolder holder, int position) {
        Reservation reservation = reservationList.get(position);

        holder.reservationUser.setText(reservation.getReservationUser());
        holder.reservationTableNumber.setText(reservation.getReservationTableNumber());
        holder.reservationDate.setText(reservation.getReservationDate().toString());
        holder.reservationType.setText(reservation.getReservationType().toString());
        holder.reservationStatus.setText(reservation.getReservationStatus().toString());
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
