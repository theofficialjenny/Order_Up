package com.example.orderup.menu_object;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

public class OrdersRVHolder extends RecyclerView.ViewHolder {
    TextView orderId, tableNumber, customerName, items, status, timestamp;

    public OrdersRVHolder(@NonNull View itemView) {
        super(itemView);
        orderId = itemView.findViewById(R.id.order_id);
        tableNumber = itemView.findViewById(R.id.order_table_number);
        customerName = itemView.findViewById(R.id.order_customer_name);
        items = itemView.findViewById(R.id.order_items);
        status = itemView.findViewById(R.id.order_status);
        timestamp = itemView.findViewById(R.id.order_timestamp);
    }
}