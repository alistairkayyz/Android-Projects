package com.alistair.hotspots;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RateDataSource {
    private SQLiteDatabase database;
    private final RateDBHelper dbHelper;

    public RateDataSource(Context context){ dbHelper = new RateDBHelper(context); }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){ dbHelper.close(); }

    public boolean insertData(Rate rate){
        boolean didSucceed =  false;

        try {
            ContentValues initialValues = new ContentValues();

            // store set of values from the rate object to save into the database
            initialValues.put("barName", rate.getBarName());
            initialValues.put("address", rate.getAddress());
            initialValues.put("city", rate.getCity());
            initialValues.put("state", rate.getState());
            initialValues.put("zipcode", rate.getZipcode());
            initialValues.put("rateBeer", rate.getBeerRatings());
            initialValues.put("rateWine", rate.getWineRatings());
            initialValues.put("rateMusic", rate.getMusicRatings());
            initialValues.put("averageRatings", rate.getAverageRatings());

            // insert a new row of data into the database table and assign a boolean value
            didSucceed = database.insert("rating", null, initialValues) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }

        return didSucceed;
    }
}
