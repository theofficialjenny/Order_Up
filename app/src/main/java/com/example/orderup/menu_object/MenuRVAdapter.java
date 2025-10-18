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
        holder.menuCategory.setText(menu.getMenuCategory());
        holder.menuPrice.setText(menu.getMenuPrice());
        holder.menuRating.setText(String.valueOf(menu.getMenuRating()));
        holder.menuOrderCount.setText(String.valueOf(menu.getMenuOrderCount()));
    }

    @Override
    public int getItemCount()  {
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
