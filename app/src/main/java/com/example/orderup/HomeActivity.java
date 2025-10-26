package com.example.orderup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.menu_object.Menu;
import com.example.orderup.menu_object.MenuRVAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerPopular, recyclerBestOffer;
    private MenuRVAdapter adapterBest, adapterPopular;
    private List<Menu> bestList, popularList;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerPopular = findViewById(R.id.recyclerPopular);
        LinearLayoutManager layoutManagerPopulars =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerPopular.setLayoutManager(layoutManagerPopulars);

        recyclerBestOffer = findViewById(R.id.recyclerBestOffer);
        LinearLayoutManager layoutManagerBestOffers =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerBestOffer.setLayoutManager(layoutManagerBestOffers);

        bestList = new ArrayList<>();
        popularList = new ArrayList<>();
        adapterBest = new MenuRVAdapter(bestList);
        adapterPopular = new MenuRVAdapter(popularList);
        recyclerPopular.setAdapter(adapterPopular);
        recyclerBestOffer.setAdapter(adapterBest);

        db = FirebaseFirestore.getInstance();
        menuBestOffers();
        menuPopular();
    }

    private void menuBestOffers() {
        db.collection("menu_best").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        bestList.clear();
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            Menu menu = doc.toObject(Menu.class);
                            bestList.add(menu);
                        }
                        adapterBest.notifyDataSetChanged();
                        Toast.makeText(HomeActivity.this, "Loaded " + bestList.size() + " best offers", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HomeActivity.this, "Failed to load best offers: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void menuPopular() {
        db.collection("menu_popular").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        popularList.clear();
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            Menu menu = doc.toObject(Menu.class);
                            popularList.add(menu);
                        }
                        adapterPopular.notifyDataSetChanged();
                        Toast.makeText(HomeActivity.this, "Loaded " + popularList.size() + " popular items", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HomeActivity.this, "Failed to load popular items: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void openSearchActivity(View view) {
        startActivity(new Intent(this, Search.class));
    }

    public void openCartActivity(View view) {
        startActivity(new Intent(this, CartActivity.class));
    }

    public void openHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));  // Starts HomeActivity again (stays on homepage)
    }

    public void openDineInActivity(View view) {
        startActivity(new Intent(this, DineIn.class));  // Navigate to reservations
    }

    public void openUserBookings(View view) {
        startActivity(new Intent(this, DineIn.class));  // Navigate to bookings (assuming same as reservations for now)
    }
}