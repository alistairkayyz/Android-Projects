package com.alistair.mealrater;

public class MealRate {
    private int mealRateID;
    private String restaurant;
    private String meal;
    private String ratings;

    public MealRate(){
        mealRateID = -1;
    }

    public int getMealRateID() {
        return mealRateID;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
