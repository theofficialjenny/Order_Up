package com.example.orderup.menu_object;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

import java.util.List;

public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVHolder> {
    private List<Menu> menuList;

    public MenuRVAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRVHolder holder, int position) {
        Menu menu = menuList.get(position);

        holder.menuImage.setImageResource(menu.getMenuImage());
        holder.menuName.setText(menu.getMenuName());
        holder.menuDescription.setText(menu.getMenuDescription());
        holder.menuRating.setText(menu.getMenuRating() + " Rating");
        holder.menuOrders.setText(menu.getMenuOrderCount() + "+ Orders");
        holder.menuCategory.setText("Category: " + menu.getMenuCategory());  // NEW
        holder.menuPrice.setText(menu.getMenuPrice());                     // NEW

        // Show "Popular" tag if rating > 4.5
        if (menu.getMenuRating() > 4.5) {
            holder.menuTag.setVisibility(View.VISIBLE);
        } else {
            holder.menuTag.setVisibility(View.GONE);
        }

        // Add to Cart button click (placeholder - implement cart logic later)
        holder.buttonAddToCart.setOnClickListener(v -> {
            // TODO: Add to cart functionality (e.g., show Toast or navigate to CartActivity)
        });
    }

    @Override
    public int getItemCount() {
        return menuList != null ? menuList.size() : 0;
    }

    public void updateData(List<Menu> menuList) {
        this.menuList = menuList;
        notifyDataSetChanged();
    }

    public void filterList(List<Menu> filteredList) {
        this.menuList = filteredList;
        notifyDataSetChanged();
    }
}