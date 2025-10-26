package com.example.orderup.menu_object;  // Assuming this is your package

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.orderup.menu_object.CartItem;
import com.example.orderup.R;
import java.util.List;

public class CartRVAdapter extends RecyclerView.Adapter<CartRVAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private Runnable updateTotalCallback;

    public CartRVAdapter(List<CartItem> cartItems, Runnable updateTotalCallback) {
        this.cartItems = cartItems;
        this.updateTotalCallback = updateTotalCallback;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemPrice.setText("Unit Price: $" + String.format("%.2f", item.getPrice()));
        holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
        holder.itemTotal.setText("Total: $" + String.format("%.2f", item.getPrice() * item.getQuantity()));

        Glide.with(holder.itemView.getContext())
                .load(item.getImageUrl())
                .into(holder.itemImage);

        holder.btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
                updateTotalCallback.run();
            }
        });

        holder.btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            updateTotalCallback.run();
        });

        holder.btnRemove.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            updateTotalCallback.run();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemDescription, itemPrice, itemQuantity, itemTotal;
        Button btnDecrease, btnIncrease, btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.cart_item_image);
            itemName = itemView.findViewById(R.id.cart_item_name);
            itemDescription = itemView.findViewById(R.id.cart_item_description);
            itemPrice = itemView.findViewById(R.id.cart_item_price);
            itemQuantity = itemView.findViewById(R.id.cart_item_quantity);
            itemTotal = itemView.findViewById(R.id.cart_item_total);
            btnDecrease = itemView.findViewById(R.id.btn_decrease_quantity);
            btnIncrease = itemView.findViewById(R.id.btn_increase_quantity);
            btnRemove = itemView.findViewById(R.id.btn_remove_item);
        }
    }
}