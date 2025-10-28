package com.example.orderup.menu_object;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

import java.util.List;

public class OrdersRVAdapter extends RecyclerView.Adapter<OrdersRVHolder> {

    private List<Order> orders;

    public OrdersRVAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrdersRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrdersRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersRVHolder holder, int position) {
        Order order = orders.get(position);

        holder.customerName.setText("Customer: " + order.getCustomerName());
        holder.items.setText("Items: " + order.getItems());
        holder.status.setText("Status: " + order.getStatus());
        holder.totalPrice.setText("Total Price: $" + order.getTotalPrice());
        holder.timestamp.setText("Timestamp: " + order.getTimestamp());

        // Color-code status
        switch (order.getStatus().toString()) {
            case "Pending":
                holder.status.setTextColor(Color.parseColor("#FF9800")); // Orange
                break;
            case "Preparing":
                holder.status.setTextColor(Color.parseColor("#2196F3")); // Blue
                break;
            case "Ready":
                holder.status.setTextColor(Color.parseColor("#4CAF50")); // Green
                break;
            case "Served":
                holder.status.setTextColor(Color.parseColor("#9E9E9E")); // Gray
                break;
            default:
                holder.status.setTextColor(Color.BLACK);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    // Method to update status (call from WaiterActivity)
    public void updateStatus(int position, Status newStatus) {
        if (position >= 0 && position < orders.size()) {
            orders.get(position).setStatus(newStatus);
            notifyItemChanged(position);
        }
    }
}
