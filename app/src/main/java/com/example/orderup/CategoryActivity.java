package com.example.orderup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.menu_object.Menu;
import com.example.orderup.menu_object.MenuRVAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryActivity extends AppCompatActivity {
    private List<Menu> allMenus = new ArrayList<>(); // Load from Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String category = getIntent().getStringExtra("category");
        RecyclerView recyclerCategory = findViewById(R.id.recycler_category);
        recyclerCategory.setLayoutManager(new GridLayoutManager(this, 2));

        // Filter by category (load allMenus from Firestore)
        List<Menu> filtered = allMenus.stream()
                .filter(m -> m.getMenuCategory().equals(category))
                .collect(Collectors.toList());
        MenuRVAdapter adapter = new MenuRVAdapter(filtered);
        recyclerCategory.setAdapter(adapter);
    }
}