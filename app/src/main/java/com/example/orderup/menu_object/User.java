package com.example.orderup.menu_object;

public class User {
    private String userName;
    private Role userRole;
    private int imageResId;

    public User() {}

    public User(String userName, Role userRole, int imageResId) {
        this.userName = userName;
        this.userRole = userRole;
        this.imageResId = imageResId;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public Role getUserRole() { return userRole; }
    public void setUserRole(Role userRole) { this.userRole = userRole; }

    public int getImageResId() { return imageResId; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }

    @Override
    public String toString() {
        return "User{" + "userName='" + userName + '\'' + ", userRole=" + userRole + ", imageResId=" + imageResId + '}';
    }
}