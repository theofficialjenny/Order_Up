package com.example.orderup.menu_object;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Cart;
import com.example.orderup.R;

import java.util.List;

public class CartRVAdapter extends RecyclerView.Adapter<CartRVHolder> {
    private final List<Cart> cartList;

    public CartRVAdapter(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRVHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.itemName.setText(cart.getMenu().getMenuName());
        holder.itemQuantity.setText("Qty: " + cart.getQuantity());
        holder.itemPrice.setText("$" + String.format("%.2f", cart.getTotalPrice()));
    }

    @Override
    public int getItemCount() { return cartList.size(); }
}