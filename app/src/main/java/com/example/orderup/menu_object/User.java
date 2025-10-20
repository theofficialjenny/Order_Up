package com.example.orderup.menu_object;

public class User {
    private String userID;
    private String userName;
    private Role userRole;

    public User(Role userRole, String userName) {
        this.userRole = userRole;
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
