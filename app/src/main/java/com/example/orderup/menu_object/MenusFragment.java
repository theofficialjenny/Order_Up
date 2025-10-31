package com.example.orderup.menu_object;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class MenusFragment extends Fragment {

    private RecyclerView recyclerMenus;
    private MenusRVAdapter menuAdapter;
    private List<Menu> menus;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menus, container, false); // Simple layout with RecyclerView
        recyclerMenus = view.findViewById(R.id.recyclerMenus);
        recyclerMenus.setLayoutManager(new LinearLayoutManager(getContext()));

        menus = new ArrayList<>();
        menuAdapter = new MenusRVAdapter(menus);
        recyclerMenus.setAdapter(menuAdapter);

        db = FirebaseFirestore.getInstance();
        loadMenus();

        return view;
    }

    private void loadMenus() {
        db.collection("menu_best").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                menus.clear();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Menu menu = doc.toObject(Menu.class);
                    menus.add(menu);
                }
                menuAdapter.notifyDataSetChanged();
            }
        });
    }


}