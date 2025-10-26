package com.example.orderup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservations;

    public ReservationAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);

        holder.reservationUser.setText(reservation.getUser());
        holder.reservationTableNumber.setText("Table Number: " + reservation.getTableNumber());
        holder.reservationDate.setText("Date: " + reservation.getDate());
        holder.reservationType.setText("Type: " + reservation.getType());
        holder.reservationStatus.setText("Status: " + reservation.getStatus());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    static class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView reservationUser, reservationTableNumber, reservationDate, reservationType, reservationStatus;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            reservationUser = itemView.findViewById(R.id.reservation_user);
            reservationTableNumber = itemView.findViewById(R.id.reservation_table_number);
            reservationDate = itemView.findViewById(R.id.reservation_date);
            reservationType = itemView.findViewById(R.id.reservation_type);
            reservationStatus = itemView.findViewById(R.id.reservation_status);
        }
    }
}
