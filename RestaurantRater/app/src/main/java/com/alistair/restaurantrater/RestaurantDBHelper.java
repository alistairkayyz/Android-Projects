package com.alistair.restaurantrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RestaurantDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myrestaurantrater.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_RESTAURANT =
            "CREATE TABLE restaurant (restaurantId INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL, streetAddress TEXT, city TEXT, state TEXT," +
                    "zipCode TEXT);";
    private static final String CREATE_TABLE_DISH =
            "CREATE TABLE dish (dishId INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL, type TEXT, rating TEXT, restaurantId INTEGER);";

    public RestaurantDBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESTAURANT);
        db.execSQL(CREATE_TABLE_DISH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RestaurantDBHelper.class.getName(), "Upgrading database from version " +
                oldVersion + " to " + newVersion + " which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        db.execSQL("DROP TABLE IF EXISTS dish");
        onCreate(db);
    }
}
