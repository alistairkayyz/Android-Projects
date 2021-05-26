package com.alistair.myfinances;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FinancesDataSource {
    private SQLiteDatabase database;
    private final FinancesDBHelper dbHelper;

    public FinancesDataSource(Context context){dbHelper = new FinancesDBHelper(context);}

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){dbHelper.close();}

    public boolean insertCDs(CDs cDs){
        boolean didSucceed = false;

        try {
            // instantiate ContentValues object to store set of values
            ContentValues initialValues = new ContentValues();

            // get the data to be stored into a database table
            initialValues.put("accountNumber", cDs.getAccountNumber());
            initialValues.put("initialBalance", cDs.getInitBalance());
            initialValues.put("currentBalance", cDs.getCurrentBalance());
            initialValues.put("interestRate", cDs.getInterestRate());

            // insert a new row of data into the database
            didSucceed =  database.insert("finances", null, initialValues) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean insertLoans(Loans loans){
        boolean didSucceed = false;

        try {
            // instantiate ContentValues object to store set of values
            ContentValues initialValues = new ContentValues();

            // get the data to be stored into a database table
            initialValues.put("accountNumber", loans.getAccountNumber());
            initialValues.put("initialBalance", loans.getInitBalance());
            initialValues.put("currentBalance", loans.getCurrentBalance());
            initialValues.put("paymentAmount", loans.getPaymentAmount());
            initialValues.put("interestRate", loans.getInterestRate());

            // insert a new row of data into the database
            didSucceed =  database.insert("finances", null, initialValues) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean insertChecking(CheckingAccount checkingAccount){
        boolean didSucceed = false;

        try {
            // instantiate ContentValues object to store set of values
            ContentValues initialValues = new ContentValues();

            // get the data to be stored into a database table
            initialValues.put("accountNumber", checkingAccount.getAccountNumber());
            initialValues.put("currentBalance", checkingAccount.getCurrentBalance());

            // insert a new row of data into the database
            didSucceed =  database.insert("finances", null, initialValues) > 0;
        }
        catch (Exception e){
            // do nothing -will return false if there is an exception
        }
        return didSucceed;
    }
}
