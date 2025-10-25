package com.example.orderup.menu_object;

public class User {
    private String userName;
    private Role userRole;

    public User(String userName, Role userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
