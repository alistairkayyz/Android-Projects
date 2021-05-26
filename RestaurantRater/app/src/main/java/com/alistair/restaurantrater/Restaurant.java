package com.alistair.restaurantrater;

public class Restaurant {
    private final int restaurantID;
    private String restaurantName;
    private String streetAddress;
    private String city;
    private String state;
    private short zipCode;

    public Restaurant(){
        restaurantID = -1;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public short getZipCode() {
        return zipCode;
    }

    public void setZipCode(short zipCode) {
        this.zipCode = zipCode;
    }
}
