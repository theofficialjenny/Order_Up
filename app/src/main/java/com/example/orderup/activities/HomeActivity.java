package com.example.orderup.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.example.orderup.menu_object.CartItem;
import com.example.orderup.menu_object.Menu;
import com.example.orderup.menu_object.MenusRVAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerPopular, recyclerBestOffer;
    private MenusRVAdapter adapterBest, adapterPopular;
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
        recyclerPopular.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerBestOffer = findViewById(R.id.recyclerBestOffer);
        recyclerBestOffer.setLayoutManager(new GridLayoutManager(this, 2));

        bestList = new ArrayList<>();
        popularList = new ArrayList<>();
        adapterBest = new MenusRVAdapter(bestList);
        adapterPopular = new MenusRVAdapter(popularList);
        recyclerPopular.setAdapter(adapterPopular);
        recyclerBestOffer.setAdapter(adapterBest);

        adapterBest.setOnAddToCartListener(this::addToCart);
        adapterPopular.setOnAddToCartListener(this::addToCart);

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

    public void openSearchActivity(View view) {
        startActivity(new Intent(this, Search.class));
    }

    public void openCartActivity(View view) {
        startActivity(new Intent(this, CartActivity.class));
    }

    public void openHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void openDineInActivity(View view) {
        startActivity(new Intent(this, ReservationActivity.class));
    }

    public void openUserProfile(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
