package com.example.orderup;

import com.example.orderup.menu_object.Menu;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final List<Cart> cartItems = new ArrayList<>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Menu menu) {
        for (Cart item : cartItems) {
            if (item.getMenu().getMenuID().equals(menu.getMenuID())) {
                item.setQuantity(item.getQuantity() + 1);
                saveToFirebase();
                return;
            }
        }
        cartItems.add(new Cart(menu, 1));
        saveToFirebase();
    }

    public List<Cart> getCartItems() { return cartItems; }
    public void clearCart() { cartItems.clear(); saveToFirebase(); }

    private void saveToFirebase() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            db.collection("carts").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .set(cartItems);
        }
    }

    public void loadFromFirebase() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            db.collection("carts").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .get().addOnSuccessListener(document -> {
                        // Parse and load (simplified; implement full parsing)
                    });
        }
    }
}