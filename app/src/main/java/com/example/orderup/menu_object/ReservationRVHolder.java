package com.example.orderup.menu_object;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

public class ReservationRVHolder extends RecyclerView.ViewHolder {
    public TextView reservationUser, reservationTableNumber, reservationDate, reservationType, reservationStatus;
    public ReservationRVHolder(@NonNull View itemView) {
        super(itemView);
        reservationUser = itemView.findViewById(R.id.reservation_user);
        reservationTableNumber = itemView.findViewById(R.id.reservation_table_number);
        reservationDate = itemView.findViewById(R.id.reservation_date);
        reservationType = itemView.findViewById(R.id.reservation_type);
        reservationStatus = itemView.findViewById(R.id.reservation_status);
    }
}
