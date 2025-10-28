package com.example.orderup.menu_object;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.R;
import java.util.List;

public class TablesRVAdapter extends RecyclerView.Adapter<TablesRVHolder> {

    private List<Table> tables;
    private OnTableClickListener listener;  // Added: Interface for click listener

    // Added: Interface for table click
    public interface OnTableClickListener {
        void onTableClick(Table table);
    }

    public TablesRVAdapter(List<Table> tables) {
        this.tables = tables;
    }

    // Added: Setter for the click listener
    public void setOnTableClickListener(OnTableClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TablesRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new TablesRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TablesRVHolder holder, int position) {
        Table table = tables.get(position);

        holder.tableNumber.setText("Table: " + table.getTableNumber());
        holder.people.setText("People: " + table.getPeople());
        holder.items.setText("Items: " + table.getItems());
        holder.price.setText("Price: $" + table.getPrice());
        holder.time.setText("Time: " + table.getTime());
        holder.status.setText("Status: " + table.getStatus());

        // Color-code status
        if ("Pending".equals(table.getStatus())) {
            holder.status.setTextColor(Color.RED);
        } else if ("Served".equals(table.getStatus())) {
            holder.status.setTextColor(Color.GREEN);
        }

        // Added: Set click listener on the item view
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTableClick(table);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public void markServed(int position) {
        if (position >= 0 && position < tables.size()) {
            tables.get(position).setStatus("Served");
            notifyItemChanged(position);
            // Update Firebase: db.collection("tables").document(...).update("status", "Served");
        }
    }
}