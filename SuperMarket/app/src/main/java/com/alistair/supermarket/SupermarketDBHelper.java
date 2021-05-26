package com.alistair.supermarket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SupermarketDBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "mySupermarketRater.db";
    private final static int DATABASE_VERSION = 1;

    private final static String CREATE_TABLE_SUPERMARKET_DETAILS =
            "CREATE TABLE supermarketDetails ( _id integer primary key autoincrement," +
                    "superMarketName text not null, address text, city text, state text," +
                    "zipcode integer);";

    private final static String CREATE_TABLE_SUPERMARKET_RATINGS =
            "CREATE TABLE supermarketRatings ( _id integer primary key autoincrement," +
                    "liquorRating text, produceRating text, meatRating text, cheeseRating text," +
                    "easeRating text, averageRating text);";

    public SupermarketDBHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SUPERMARKET_DETAILS);
        db.execSQL(CREATE_TABLE_SUPERMARKET_RATINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SupermarketDBHelper.class.getName(), "Upgrading database from version " +
                oldVersion + " to " + newVersion + " which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS supermarketDetails");
        db.execSQL("DROP TABLE IF EXISTS supermarketRatings");
        onCreate(db);
    }
}
