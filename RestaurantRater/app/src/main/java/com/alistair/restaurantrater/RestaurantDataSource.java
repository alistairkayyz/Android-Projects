package com.alistair.restaurantrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RestaurantDataSource {
    private SQLiteDatabase database;
    private RestaurantDBHelper dbHelper;

    public RestaurantDataSource(Context context){
        dbHelper = new RestaurantDBHelper(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public boolean insertRestaurantData(Restaurant restaurant){
        boolean didSucceed = false;

        try {
            ContentValues initialValues = new ContentValues();

            // store a set of values to be inserted into the database
            initialValues.put("name", restaurant.getRestaurantName());
            initialValues.put("streetAddress", restaurant.getStreetAddress());
            initialValues.put("city", restaurant.getCity());
            initialValues.put("state", restaurant.getState());
            initialValues.put("zipCode", restaurant.getZipCode());

            // inserts a new row of data into the database
            didSucceed = database.insert("restaurant", null, initialValues) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }
    public boolean insertDishData(Dish dish){
        boolean didSucceed = false;

        try {
            ContentValues initialValues = new ContentValues();

            // stores a set of values to be inserted into the database
            initialValues.put("name", dish.getDishName());
            initialValues.put("type", dish.getDishType());
            initialValues.put("rating", dish.getRating());
            initialValues.put("restaurantId", dish.getRestaurantId());

            // inserts a new row of data into the database
            didSucceed = database.insert("dish", null, initialValues) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    // gets the last ID in the database
    public int getLastRestaurantId(){
        int lastId;

        try {
            // query the last ID
            String query = "Select MAX(restaurantId) from restaurant";

            // move the cursor to the first column the assigns the value to lastId
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastId = cursor.getInt(0);

            cursor.close(); // close the cursor object
        }
        catch (Exception e){
            lastId = -1;
        }

        return lastId;
    }
}
