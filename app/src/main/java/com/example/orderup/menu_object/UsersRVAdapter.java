package com.example.orderup.menu_object;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.R;
import java.util.List;

public class UsersRVAdapter extends RecyclerView.Adapter<UsersRVHolder> {

    private List<User> users;

    public UsersRVAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UsersRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UsersRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersRVHolder holder, int position) {
        User user = users.get(position);
        holder.userName.setText(user.getUserName());
        holder.userImage.setImageResource(user.getImageResId() != 0 ? user.getImageResId() : R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
