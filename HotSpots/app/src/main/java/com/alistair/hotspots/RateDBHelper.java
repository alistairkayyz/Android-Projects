package com.alistair.hotspots;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RateDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myratings.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_RATE =
            "create table rating (_id integer primary key autoincrement," +
                    "barName text not null, address text, city text, state text, " +
                    "zipcode integer, rateBeer text, rateWine text, " +
                    "rateMusic text, averageRatings text);";

    public RateDBHelper(Context context){ super(context, DATABASE_NAME,null,DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RateDBHelper.class.getName(), "Upgrading database from version " +
                oldVersion + " to " + newVersion + " which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS rating");
        onCreate(db);
    }
}
