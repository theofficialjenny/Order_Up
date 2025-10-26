package com.example.orderup.menu_object;

public class User {
    private String name;
    private Role role;
    private int imageRefId;

    public User(String name, Role role, int imageRefId) {
        this.name = name;
        this.role = role;
        this.imageRefId = imageRefId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getImageRefId() {
        return imageRefId;
    }

    public void setImageRefId(int imageRefId) {
        this.imageRefId = imageRefId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", role=" + role +
                ", imageRefId=" + imageRefId +
                '}';
    }
}
