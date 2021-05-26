package com.alistair.myfinances;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FinancesDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myfinances.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_FINANCES =
            "create table finances (_id integer primary key autoincrement," +
                    "accountNumber text not null, initialBalance text," +
                    "currentBalance text, paymentAmount text," +
                    "interestRate text);";

    public FinancesDBHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FINANCES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(FinancesDBHelper.class.getName(), "Upgrading database from version " +
                oldVersion + " to " + newVersion + " which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS finances");
        onCreate(db);
    }
}
