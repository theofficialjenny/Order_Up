package com.example.orderup;

public class Order {
    private String id;
    private String customerName;
    private String items;
    private String status;
    private String timestamp;
    private int tableNumber;

    public Order(String id, int tableNumber, String customerName, String items, String status, String timestamp) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.customerName = customerName;
        this.items = items;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getTableNumber() { return tableNumber; }
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}