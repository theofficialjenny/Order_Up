package com.example.orderup.menu_object;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderup.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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

        holder.btnDeleteUser.setOnClickListener(v -> {
            FirebaseFirestore.getInstance().collection("users")
                    .whereEqualTo("userName", user.getUserName())
                    .get()
                    .addOnSuccessListener(query -> {
                        for (DocumentSnapshot doc : query.getDocuments()) {
                            doc.getReference().delete();
                        }
                        users.remove(position);
                        notifyItemRemoved(position);
                    });
        });



    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
