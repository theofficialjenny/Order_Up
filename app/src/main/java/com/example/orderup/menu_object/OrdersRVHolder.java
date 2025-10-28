package com.example.orderup.menu_object;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

public class OrdersRVHolder extends RecyclerView.ViewHolder {
    TextView orderId, tableNumber, customerName, items, status, totalPrice, timestamp;

    public OrdersRVHolder(@NonNull View itemView) {
        super(itemView);
        customerName = itemView.findViewById(R.id.order_customer_name);
        items = itemView.findViewById(R.id.order_items);
        status = itemView.findViewById(R.id.order_status);
        totalPrice = itemView.findViewById(R.id.order_total_price);
        timestamp = itemView.findViewById(R.id.order_timestamp);
    }
}