package com.alistair.mealrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MealRateDataSource {
    private SQLiteDatabase database;
    private final DBMealRaterHelper dbHelper;

    public MealRateDataSource(Context context){ dbHelper = new DBMealRaterHelper(context); }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    // this methods inserts new row of data into the database table
    public boolean insertData(MealRate mealRate){
            boolean didSucceed = false;

            try{
                ContentValues initValues = new ContentValues();

                // store a set of values tot be inserted into the database
                initValues.put("restaurant", mealRate.getRestaurant());
                initValues.put("meal", mealRate.getMeal());
                initValues.put("ratings", mealRate.getRatings());

                // inserts a new row of data into the database table
                didSucceed =  database.insert("meal_rate", null, initValues) > 0;
            }
            catch (Exception e){
                // do nothing -will return false if there is an exception
            }
            return didSucceed;
    }

    // this method updates currently selected row in the database table
    public boolean updateData(MealRate mealRate){
        long rowId = mealRate.getMealRateID();
        boolean didSucceed = false;

        try{
            ContentValues updateValues = new ContentValues();

            // stores set of newly updated values
            updateValues.put("restaurant", mealRate.getRestaurant());
            updateValues.put("meal", mealRate.getMeal());
            updateValues.put("ratings", mealRate.getRatings());

            // update the data in the currently selected row
            didSucceed =  database.update("meal_rate", updateValues, String.valueOf(rowId), null) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

}
