package com.example.orderup.menu_object;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.R;

public class TablesRVHolder extends RecyclerView.ViewHolder {
    TextView tableNumber, status, people, items, price, time;

    public TablesRVHolder(@NonNull View itemView) {
        super(itemView);
        tableNumber = itemView.findViewById(R.id.table_number);
        status = itemView.findViewById(R.id.table_status);
        people = itemView.findViewById(R.id.table_people);
        items = itemView.findViewById(R.id.table_items);
        price = itemView.findViewById(R.id.table_price);
        time = itemView.findViewById(R.id.table_time);
    }
}