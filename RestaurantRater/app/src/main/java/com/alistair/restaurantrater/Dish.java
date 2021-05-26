package com.alistair.restaurantrater;

public class Dish {
    private final int dishId;
    private String dishName;
    private String dishType;
    private float rating;
    private int restaurantId;

    public Dish(){
        dishId = -1;
    }

    public int getDishId() {
        return dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
