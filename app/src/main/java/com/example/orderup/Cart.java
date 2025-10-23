package com.example.orderup;

import com.example.orderup.menu_object.Menu;

public class Cart {
    private final Menu menu;
    private int quantity;

    public Cart(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() { return menu; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return Double.parseDouble(menu.getMenuPrice().replace("$", "")) * quantity; }
}