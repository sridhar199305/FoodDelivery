package com.example.restdemo.model;

public class Restaurent {

    private Long id;
    private String name;

    private Long MenuId;

    private int rating;

    private int AvgPrice;

    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMenuId() {
        return MenuId;
    }

    public void setMenuId(Long menuId) {
        MenuId = menuId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getAvgPrice() {
        return AvgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        AvgPrice = avgPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
