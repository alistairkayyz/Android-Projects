package com.alistair.mycontactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContactDataSource {
    private SQLiteDatabase database;
    private final ContactDBHelper dbHelper;

    public ContactDataSource(Context context){
        dbHelper = new ContactDBHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    // inserts new data into the database table
    // and returns true if it succeeds
    public boolean insertContact(Contact c){
        boolean didSucceed = false;

        try {
            ContentValues initialValues = new ContentValues();

            // stores a set of values to be inserted into the database table
            initialValues.put("contactname", c.getContactName());
            initialValues.put("streetaddress", c.getStreetAddress());
            initialValues.put("city", c.getCity());
            initialValues.put("state", c.getState());
            initialValues.put("zipcode", c.getZipcode());
            initialValues.put("phonenumber", c.getPhoneNumber());
            initialValues.put("cell", c.getCellNumber());
            initialValues.put("email", c.getEmail());
            initialValues.put("birthday", String.valueOf(c.getBirthday().getTimeInMillis()));

            /* inserts contact into the database table and
            sets didSucceed to true if database row is greater than zero */
            didSucceed =  database.insert("contact", null, initialValues) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    // inserts new data into the database table
    // and returns true if it succeeds
    public boolean updateContact(Contact c){
        boolean didSucceed = false;

        try {
            long rowId = c.getContactID();
            ContentValues updateValues = new ContentValues();

            // stores a set of values to update the current row in the database table
            updateValues.put("contactname", c.getContactName());
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode", c.getZipcode());
            updateValues.put("phonenumber", c.getPhoneNumber());
            updateValues.put("cell", c.getCellNumber());
            updateValues.put("email", c.getEmail());
            updateValues.put("birthday", String.valueOf(c.getBirthday().getTimeInMillis()));

            /* updates current contact in the database table and
            sets didSucceed to true if database row is greater than zero */
            didSucceed =  database.update("contact", updateValues, String.valueOf(rowId), null) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }
    public boolean updateAddress(Contact c){
        boolean didSucceed = false;

        try {
            long rowId = c.getContactID();
            ContentValues updateValues = new ContentValues();

            // stores a set of values to update the current row address in the database table
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode", c.getZipcode());

            /* updates current contact address in the database table and
            sets didSucceed to true if database row is greater than zero */
            didSucceed =  database.update("contact", updateValues, String.valueOf(rowId), null) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public int getLastContactID(){
        int lastId;

        try {
            // query last contact Id
            String query = "Select MAX(_id) from contact";
            Cursor cursor = database.rawQuery(query, null); // get the cursor

            // move the cursor to the first column and assign the id to lastId variable
            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e){
            lastId = -1; // set the id to -1 when an exception is caught
        }

        return lastId; // return the last id
    }
}
