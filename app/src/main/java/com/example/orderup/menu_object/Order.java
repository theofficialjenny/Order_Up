package com.example.orderup.menu_object;

public class Order {
    private String customerName;
    private String items;
    private String status;
    private String timestamp;
    private int tableNumber;

    public Order(int tableNumber, String customerName, String items, String status, String timestamp) {
        this.tableNumber = tableNumber;
        this.customerName = customerName;
        this.items = items;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Getters and setters

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

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", items='" + items + '\'' +
                ", status='" + status + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", tableNumber=" + tableNumber +
                '}';
    }
}