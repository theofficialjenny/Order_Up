package com.example.orderup;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.orderId.setText("Order ID: " + order.getId());
        holder.tableNumber.setText("Table Number: " + order.getTableNumber());
        holder.customerName.setText("Customer: " + order.getCustomerName());
        holder.items.setText("Items: " + order.getItems());
        holder.status.setText("Status: " + order.getStatus());
        holder.timestamp.setText("Timestamp: " + order.getTimestamp());

        // Color-code status
        switch (order.getStatus()) {
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
    public void updateStatus(int position, String newStatus) {
        if (position >= 0 && position < orders.size()) {
            orders.get(position).setStatus(newStatus);
            notifyItemChanged(position);
        }
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, tableNumber, customerName, items, status, timestamp;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id);
            tableNumber = itemView.findViewById(R.id.order_table_number);
            customerName = itemView.findViewById(R.id.order_customer_name);
            items = itemView.findViewById(R.id.order_items);
            status = itemView.findViewById(R.id.order_status);
            timestamp = itemView.findViewById(R.id.order_timestamp);
        }
    }
}
