package com.example.orderup.menu_object;

public class Table {
    private int tableNumber;
    private String status;  // "Pending" or "Served"
    private int people;
    private String items;  // e.g., "Burger x2, Fries x1"
    private double price;
    private String time;  // e.g., "14:30"

    public Table() {}

    public Table(int tableNumber, String status, int people, String items, double price, String time) {
        this.tableNumber = tableNumber;
        this.status = status;
        this.people = people;
        this.items = items;
        this.price = price;
        this.time = time;
    }

    // Getters and setters
    public int getTableNumber() { return tableNumber; }
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getPeople() { return people; }
    public void setPeople(int people) { this.people = people; }

    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}