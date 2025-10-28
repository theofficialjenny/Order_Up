package com.example.orderup.menu_object;

public class Order {
    private String customerName;
    private String items;
    private Status status;
    private String timestamp;
    private double totalPrice;

    public Order(String customerName, String items, Status status, double totalPrice, String timestamp) {
        this.customerName = customerName;
        this.items = items;
        this.status = status;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public double getTotalPrice() { return totalPrice; }

    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", items='" + items + '\'' +
                ", status=" + status +
                ", timestamp='" + timestamp + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}