package com.alistair.hotspots;

public class Rate {
    private final int _id;
    private String barName;
    private String address;
    private String city;
    private String state;
    private int zipcode;
    private float averageRatings;
    private float beerRatings;
    private float wineRatings;
    private float musicRatings;

    public Rate(){
        _id = -1;
    }

    public int get_id() {
        return _id;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getAverageRatings() {
        return averageRatings;
    }

    public void setAverageRatings(float beerRatings, float wineRatings, float musicRatings) {
        this.averageRatings = (beerRatings + wineRatings + musicRatings)/3;
    }

    public float getBeerRatings() {
        return beerRatings;
    }

    public void setBeerRatings(float beerRatings) {
        this.beerRatings = beerRatings;
    }

    public float getWineRatings() {
        return wineRatings;
    }

    public void setWineRatings(float wineRatings) {
        this.wineRatings = wineRatings;
    }

    public float getMusicRatings() {
        return musicRatings;
    }

    public void setMusicRatings(float musicRatings) {
        this.musicRatings = musicRatings;
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

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
