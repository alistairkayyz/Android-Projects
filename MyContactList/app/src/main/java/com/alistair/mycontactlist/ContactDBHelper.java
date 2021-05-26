package com.alistair.mycontactlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_CONTACTS =
            "create table contact (_id integer primary key autoincrement, " +
                    "contactname text not null, streetaddress text, city text," +
                    "state text, zipcode text, phonenumber text, cell text," +
                    "email text, birthday text);";

    public ContactDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // insert a new column
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE contact ADD COLUMN bestFriendForever INTEGER DEFAULT 0");
        }
        Log.w(ContactDBHelper.class.getName(), "Upgrading database from version " +
                oldVersion + " to " + newVersion + " which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }
}
