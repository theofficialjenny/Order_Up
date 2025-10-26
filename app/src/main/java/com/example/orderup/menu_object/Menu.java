package com.example.orderup.menu_object;

public class Menu {
    private String menuName;
    private String menuDescription;
    private String menuCategory;
    private String menuPrice;
    private float menuRating;
    private String menuImage;
    private int menuOrderCount;

    public Menu(String menuName, String menuDescription, String menuCategory, String menuImage, String menuPrice, float menuRating, int menuOrderCount) {
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.menuCategory = menuCategory;
        this.menuImage = menuImage;
        this.menuPrice = menuPrice;
        this.menuRating = menuRating;
        this.menuOrderCount = menuOrderCount;
    }

    public Menu(){

    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public float getMenuRating() {
        return menuRating;
    }

    public void setMenuRating(float menuRating) {
        this.menuRating = menuRating;
    }

    public int getMenuOrderCount() {
        return menuOrderCount;
    }

    public void setMenuOrderCount(int menuOrderCount) {
        this.menuOrderCount = menuOrderCount;
    }

    @Override
    public String toString() {
        return "Menu{" +
                ", menuName='" + menuName + '\'' +
                ", menuDescription='" + menuDescription + '\'' +
                ", menuCategory='" + menuCategory + '\'' +
                ", menuImage='" + menuImage + '\'' +
                ", menuPrice='" + menuPrice + '\'' +
                ", menuRating=" + menuRating + '\'' +
                ", menuOrderCount=" + menuOrderCount +
                '}';
    }
}
