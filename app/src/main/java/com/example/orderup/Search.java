package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.menu_object.Menu;
import com.example.orderup.menu_object.MenuRVAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Search extends AppCompatActivity {

    private RecyclerView recyclerBestOffers, recyclerPopularNearby, recyclerPizza;
    private MenuRVAdapter bestOffersAdapter, popularNearbyAdapter, pizzaAdapter;
    private List<Menu> allMenus, bestOffersList, popularNearbyList, pizzaList;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        searchBar = findViewById(R.id.search_bar);
        recyclerBestOffers = findViewById(R.id.recycler_best_offers);
        recyclerPopularNearby = findViewById(R.id.recycler_popular_nearby);
        recyclerPizza = findViewById(R.id.recycler_category_pizza);

        // Set layouts: Horizontal for best offers and popular nearby, vertical for pizza
        recyclerBestOffers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerPopularNearby.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerPizza.setLayoutManager(new LinearLayoutManager(this));

        // Sample master list of menus (in a real app, load from Firebase)
        allMenus = new ArrayList<>();
        allMenus.add(new Menu("Burger", "Tasty burger", "Fast Food", R.drawable.ic_launcher_foreground, "$10.00", 4.8f, 150));
        allMenus.add(new Menu("Pizza Margherita", "Classic pizza", "Pizza", R.drawable.ic_launcher_foreground, "$15.00", 4.9f, 200));
        allMenus.add(new Menu("Pasta", "Italian pasta", "Italian", R.drawable.ic_launcher_foreground, "$12.00", 4.6f, 180));
        allMenus.add(new Menu("Chicken Wings", "Spicy wings", "Appetizer", R.drawable.ic_launcher_foreground, "$8.00", 4.7f, 120));

        // Filter for sections
        bestOffersList = allMenus.stream().filter(m -> m.getMenuRating() > 4.7).collect(Collectors.toList());  // High-rated
        popularNearbyList = allMenus.stream().filter(m -> m.getMenuOrderCount() > 140).collect(Collectors.toList());  // High-orders
        pizzaList = allMenus.stream().filter(m -> "Pizza".equals(m.getMenuCategory())).collect(Collectors.toList());  // Pizza category

        // Set adapters to bind data to RecyclerViews
        bestOffersAdapter = new MenuRVAdapter(bestOffersList);
        recyclerBestOffers.setAdapter(bestOffersAdapter);

        popularNearbyAdapter = new MenuRVAdapter(popularNearbyList);
        recyclerPopularNearby.setAdapter(popularNearbyAdapter);

        pizzaAdapter = new MenuRVAdapter(pizzaList);
        recyclerPizza.setAdapter(pizzaAdapter);

        // "See All" click listeners for sections
        findViewById(R.id.tv_best_offers).setOnClickListener(v -> openSeeAll(bestOffersList, "Best Offers"));
        findViewById(R.id.tv_popular_food_nearby).setOnClickListener(v -> openSeeAll(popularNearbyList, "Popular Nearby"));

        // Search functionality: Filter all lists on text change
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterMenus(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Method to open "See All" activity for a section
    private void openSeeAll(List<Menu> items, String title) {
        Intent intent = new Intent(this, SeeAllActivity.class);
        intent.putExtra("items", (Serializable) items);
        intent.putExtra("section", title);
        startActivity(intent);
    }

    // Filter all RecyclerViews based on search query
    private void filterMenus(String query) {
        List<Menu> filtered = allMenus.stream()
                .filter(m -> m.getMenuName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

        // Update all adapters with filtered list
        bestOffersAdapter.filterList(filtered.stream().filter(m -> m.getMenuRating() > 4.7).collect(Collectors.toList()));
        popularNearbyAdapter.filterList(filtered.stream().filter(m -> m.getMenuOrderCount() > 140).collect(Collectors.toList()));
        pizzaAdapter.filterList(filtered.stream().filter(m -> "Pizza".equals(m.getMenuCategory())).collect(Collectors.toList()));
    }
}