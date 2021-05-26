package com.alistair.supermarket;

public class Supermarket {
    private final int _id;
    private String superMarketName;
    private String address;
    private String city;
    private String state;
    private int zipcode;
    float liquorRating;
    float produceRating;
    float meatRating;
    float cheeseRating;
    float easeOfCheckoutRating;
    float averageRating;

    public Supermarket(){
        _id = -1;
    }

    public int get_id() {
        return _id;
    }

    public String getSuperMarketName() {
        return superMarketName;
    }

    public void setSuperMarketName(String superMarketName) {
        this.superMarketName = superMarketName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLiquorRating() {
        return liquorRating;
    }

    public void setLiquorRating(float liquorRating) {
        this.liquorRating = liquorRating;
    }

    public float getProduceRating() {
        return produceRating;
    }

    public void setProduceRating(float produceRating) {
        this.produceRating = produceRating;
    }

    public float getMeatRating() {
        return meatRating;
    }

    public void setMeatRating(float meatRating) {
        this.meatRating = meatRating;
    }

    public float getCheeseRating() {
        return cheeseRating;
    }

    public void setCheeseRating(float cheeseRating) {
        this.cheeseRating = cheeseRating;
    }

    public float getEaseOfCheckoutRating() {
        return easeOfCheckoutRating;
    }

    public void setEaseOfCheckoutRating(float easeOfCheckoutRating) {
        this.easeOfCheckoutRating = easeOfCheckoutRating;
    }

    public void setAverageRating(float liquorRating, float produceRating,
                                 float meatRating, float cheeseRating,
                                 float easeOfCheckoutRating) {
        this.averageRating = (liquorRating + produceRating + meatRating +
                cheeseRating + easeOfCheckoutRating) / 5;
    }

    public float getAverageRating() {
        return averageRating;
    }
}
