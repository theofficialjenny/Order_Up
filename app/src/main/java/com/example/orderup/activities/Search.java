package com.example.orderup.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.example.orderup.menu_object.CartItem;  // Added: Import CartItem class
import com.example.orderup.menu_object.Menu;
import com.example.orderup.menu_object.MenusRVAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {  // Removed <CartItem> generic
    private RecyclerView recyclerItem;
    private MenusRVAdapter adapter;
    private List<Menu> allMenus, filteredMenus;
    private EditText searchBar;
    private FirebaseFirestore db;

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

        searchBar = findViewById(R.id.search_bar_home);
        recyclerItem = findViewById(R.id.recyclerItem);
        recyclerItem.setLayoutManager(new GridLayoutManager(this, 2));

        allMenus = new ArrayList<>();
        filteredMenus = new ArrayList<>();
        adapter = new MenusRVAdapter(filteredMenus);
        recyclerItem.setAdapter(adapter);

        adapter.setOnAddToCartListener(this::addToCart);

        db = FirebaseFirestore.getInstance();
        loadAllMenus();

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

    private void loadAllMenus() {
        allMenus.clear();
        db.collection("menu_popular").get().addOnCompleteListener(popularTask -> {
            if (popularTask.isSuccessful()) {
                for (QueryDocumentSnapshot doc : popularTask.getResult()) {
                    Menu menu = doc.toObject(Menu.class);
                    allMenus.add(menu);
                }
                db.collection("menu_best").get().addOnCompleteListener(bestTask -> {
                    if (bestTask.isSuccessful()) {
                        for (QueryDocumentSnapshot doc : bestTask.getResult()) {
                            Menu menu = doc.toObject(Menu.class);
                            allMenus.add(menu);
                        }
                        filteredMenus.addAll(allMenus);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(Search.this, "Loaded " + allMenus.size() + " items", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Search.this, "Failed to load best offers: " + bestTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(Search.this, "Failed to load popular items: " + popularTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterMenus(String query) {
        filteredMenus.clear();
        if (query.isEmpty()) {
            filteredMenus.addAll(allMenus);
        } else {
            for (Menu menu : allMenus) {
                if (menu.getMenuName().toLowerCase().contains(query.toLowerCase()) ||
                        menu.getMenuDescription().toLowerCase().contains(query.toLowerCase()) ||
                        menu.getMenuCategory().toLowerCase().contains(query.toLowerCase())) {
                    filteredMenus.add(menu);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void addToCart(Menu menu) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();

        String cartJson = prefs.getString("cart_items", "[]");
        List<CartItem> cartItems = gson.fromJson(cartJson, new TypeToken<List<CartItem>>(){}.getType());

        boolean itemExists = false;
        for (CartItem item : cartItems) {
            if (item.getName().equals(menu.getMenuName())) {
                item.setQuantity(item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            double price = Double.parseDouble(menu.getMenuPrice().replace("$", ""));
            CartItem newItem = new CartItem(menu.getMenuName(), menu.getMenuDescription(), price, 1, menu.getMenuImage());
            cartItems.add(newItem);
        }

        String updatedCartJson = gson.toJson(cartItems);
        editor.putString("cart_items", updatedCartJson);
        editor.apply();

        Toast.makeText(this, menu.getMenuName() + " added to cart!", Toast.LENGTH_SHORT).show();
    }

    public void openHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void openCartActivity(View view) {
        startActivity(new Intent(this, CartActivity.class));
    }
}
