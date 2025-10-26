package com.example.orderup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerUsers;
    private UserAdapter userAdapter; // Assume you have this adapter; similar to CartAdapter
    private List<User> users; // Assume User model class

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false); // Create a simple layout with RecyclerView
        recyclerUsers = view.findViewById(R.id.recyclerUsers);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        users = new ArrayList<>();
        // Load users from Firebase or database, e.g., users.add(new User("John Doe"));
        userAdapter = new UserAdapter(users);
        recyclerUsers.setAdapter(userAdapter);

        return view;
    }
}