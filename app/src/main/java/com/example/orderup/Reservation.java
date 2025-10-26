package com.example.orderup;

public class Reservation {
    private String user;
    private int tableNumber;
    private String date;
    private String type;
    private String status;

    public Reservation(String user, int tableNumber, String date, String type, String status) {
        this.user = user;
        this.tableNumber = tableNumber;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    // Getters and setters
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public int getTableNumber() { return tableNumber; }
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
