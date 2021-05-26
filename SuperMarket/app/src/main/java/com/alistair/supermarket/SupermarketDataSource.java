package com.alistair.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SupermarketDataSource {
    private SQLiteDatabase database;
    private final SupermarketDBHelper dbHelper;

    public SupermarketDataSource(Context context){
        dbHelper = new SupermarketDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public boolean insertRatingsData(Supermarket s){
        boolean didSucceed = false;

        try {
            // instantiate ContentValues object
            ContentValues initialValues = new ContentValues();

            // store a set of values to be inserted into the database
            initialValues.put("liquorRating", s.getLiquorRating());
            initialValues.put("produceRating", s.getProduceRating());
            initialValues.put("meatRating", s.getMeatRating());
            initialValues.put("cheeseRating", s.getCheeseRating());
            initialValues.put("easeRating", s.getEaseOfCheckoutRating());
            initialValues.put("averageRating", s.getAverageRating());

            // insert a new row of data into the database and return true if it succeeds or false otherwise
            didSucceed =  database.insert("supermarketRatings", null, initialValues) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean insertDetailsData(Supermarket s){
        boolean didSucceed = false;

        try {
            // instantiate ContentValues object
            ContentValues initialValues = new ContentValues();

            // store a set of values to be inserted into the database
            initialValues.put("superMarketName", s.getSuperMarketName());
            initialValues.put("address", s.getAddress());
            initialValues.put("city", s.getCity());
            initialValues.put("state", s.getState());
            initialValues.put("zipcode", s.getZipcode());

            // insert a new row of data into the database and return true if it succeeds or false otherwise
            didSucceed =  database.insert("supermarketDetails", null, initialValues) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }
}
