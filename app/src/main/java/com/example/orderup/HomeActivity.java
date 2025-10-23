package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.menu_object.Menu;
import com.example.orderup.menu_object.MenuRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

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

        // Initialize RecyclerViews
        RecyclerView recyclerBestOffers = findViewById(R.id.recycler_best_offers);  // For "Best Offers" (horizontal)
        RecyclerView recyclerPopular = findViewById(R.id.recyclerItem);             // For "Our Populars" (vertical)

        // Set layouts: Horizontal for best offers, vertical for popular
        recyclerBestOffers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerPopular.setLayoutManager(new LinearLayoutManager(this));

        // Sample data for "Best Offers" (high-rated items)
        List<Menu> bestOffersList = new ArrayList<>();
        bestOffersList.add(new Menu("Burger Deal", "Juicy burger with fries", "Fast Food", R.drawable.ic_launcher_foreground, "$10.00", 4.8f, 150));
        bestOffersList.add(new Menu("Pizza Special", "Cheesy pizza", "Italian", R.drawable.ic_launcher_foreground, "$15.00", 4.9f, 200));

        // Sample data for "Our Populars" (high-order items)
        List<Menu> popularList = new ArrayList<>();
        popularList.add(new Menu("Chicken Wings", "Spicy wings", "Appetizer", R.drawable.ic_launcher_foreground, "$8.00", 4.7f, 120));
        popularList.add(new Menu("Pasta", "Creamy pasta", "Italian", R.drawable.ic_launcher_foreground, "$12.00", 4.6f, 180));

        // Set adapters to bind data to RecyclerViews
        MenuRVAdapter bestOffersAdapter = new MenuRVAdapter(bestOffersList);
        recyclerBestOffers.setAdapter(bestOffersAdapter);

        MenuRVAdapter popularAdapter = new MenuRVAdapter(popularList);
        recyclerPopular.setAdapter(popularAdapter);
    }

    public void openSearchActivity(View view) {
        startActivity(new Intent(this, Search.class));
    }

    // Removed redundant openHomeActivity (already on Home)

    public void openCartActivity(View view) {
        // TODO: Implement CartActivity (e.g., startActivity(new Intent(this, CartActivity.class));)
        // For now, show a placeholder Toast or navigate
    }

    public void openDineInActivity(View view) {
        startActivity(new Intent(this, DineIn.class));
    }

    public void openHomeActivity(View view) {
    }
}