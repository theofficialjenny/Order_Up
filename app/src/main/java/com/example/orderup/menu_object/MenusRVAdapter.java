package com.example.orderup.menu_object;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.orderup.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MenusRVAdapter extends RecyclerView.Adapter<MenusRVHolder> {
    private List<Menu> menuList;
    private OnAddToCartListener addToCartListener;

    public interface OnAddToCartListener {
        void onAddToCart(Menu menu);
    }

    public void setOnAddToCartListener(OnAddToCartListener listener) {
        this.addToCartListener = listener;
    }

    public MenusRVAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenusRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenusRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenusRVHolder holder, int position) {
        Menu menu = menuList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(menu.getMenuImage())
                .into(holder.menuImage);

        holder.menuName.setText(menu.getMenuName());
        holder.menuDescription.setText(menu.getMenuDescription());
        holder.menuCategory.setText(menu.getMenuCategory());
        holder.menuPrice.setText(menu.getMenuPrice());
        holder.menuRating.setText(String.valueOf(menu.getMenuRating()));
        holder.menuOrderCount.setText(String.valueOf(menu.getMenuOrderCount()));

        holder.btnAddToCart.setOnClickListener(v -> {
            if (addToCartListener != null) {
                addToCartListener.onAddToCart(menu);
            }
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