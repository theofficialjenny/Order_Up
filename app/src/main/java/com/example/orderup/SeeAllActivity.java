package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.menu_object.Menu;
import com.example.orderup.menu_object.MenuRVAdapter;

import java.util.List;

public class SeeAllActivity extends AppCompatActivity {

    @SuppressWarnings({"deprecation", "unchecked"})  // Suppress warnings for Serializable and unchecked cast
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        String section = getIntent().getStringExtra("section");
        RecyclerView recyclerSeeAll = findViewById(R.id.recycler_see_all);
        recyclerSeeAll.setLayoutManager(new LinearLayoutManager(this));

        // Safely cast the Serializable extra to List<Menu>
        List<Menu> items = (List<Menu>) getIntent().getSerializableExtra("items");
        if (items != null) {
            MenuRVAdapter adapter = new MenuRVAdapter(items);
            recyclerSeeAll.setAdapter(adapter);
        }
    }
}
