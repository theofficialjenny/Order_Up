package com.example.orderup.menu_object;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.R;

public class UsersRVHolder extends RecyclerView.ViewHolder {
    ImageView userImage;
    TextView userName;
    Button btnDeleteUser;

    public UsersRVHolder(@NonNull View itemView) {
        super(itemView);
        userImage = itemView.findViewById(R.id.user_image);
        userName = itemView.findViewById(R.id.user_name);
        btnDeleteUser = itemView.findViewById(R.id.btnDeleteUser);
    }
}
