package com.alistair.mealrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBMealRaterHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mymealrating.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_MEAL_RATE =
            "create table meal_rate (_id integer primary key autoincrement, " +
                    "restaurant text not null, meal text, ratings text);";


    public DBMealRaterHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE_MEAL_RATE); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBMealRaterHelper.class.getName(), "Upgrading database from version " +
                oldVersion + " to " + newVersion + " which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS meal_rate");
        onCreate(db);
    }
}
