package com.alistair.myfinances;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CDs cDs;
    private Loans loans;
    private CheckingAccount checkingAccount;

    EditText etAccountNumber;
    EditText etInitBalance;
    EditText etCurrentBalance;
    EditText etPaymentAmount;
    EditText etInterestRate;
    Button btnSave;
    Button btnCancel;
    RadioButton radCDs;
    RadioButton radLoans;
    RadioButton radChecking;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate class objects
        cDs = new CDs();
        loans = new Loans();
        checkingAccount = new CheckingAccount();

        // instantiates widget objects
        etAccountNumber = findViewById(R.id.editAccountNumber);
        etInitBalance = findViewById(R.id.editInitBalance);
        etCurrentBalance = findViewById(R.id.editCurrentBalance);
        etPaymentAmount = findViewById(R.id.editPaymentAmount);
        etInterestRate = findViewById(R.id.editInterestRate);

        btnSave = findViewById(R.id.buttonSave);
        btnCancel = findViewById(R.id.buttonCancel);

        radCDs = findViewById(R.id.radioCDs);
        radLoans = findViewById(R.id.radioLoans);
        radChecking = findViewById(R.id.radioChecking);

        initDisable(false); // disables all widgets
        initSaveButton(); // called to save data
        initCancelButton();
        initTextChangedEvent(); // called to monitor any changes in the text

        radioGroup = findViewById(R.id.radioGroup);

        // monitors the checked radio button and then enables and disables related fields
        radioGroup.setOnCheckedChangeListener(((group, checkedId) -> {
            if (radCDs.isChecked()){ // if true enables CDs text fields
                etAccountNumber.setEnabled(true);
                etInitBalance.setEnabled(true);
                etCurrentBalance.setEnabled(true);
                etInterestRate.setEnabled(true);
                etPaymentAmount.setEnabled(false);
                btnCancel.setEnabled(true);
                btnSave.setEnabled(true);
            }
            else if (radChecking.isChecked()){ // if true enables Checking text fields
                etAccountNumber.setEnabled(true);
                etInitBalance.setEnabled(false);
                etCurrentBalance.setEnabled(true);
                etInterestRate.setEnabled(false);
                etPaymentAmount.setEnabled(false);
                btnCancel.setEnabled(true);
                btnSave.setEnabled(true);
            }
            else if (radLoans.isChecked()){ // if true enables Loan text fields
                etAccountNumber.setEnabled(true);
                etInitBalance.setEnabled(true);
                etCurrentBalance.setEnabled(true);
                etPaymentAmount.setEnabled(true);
                etInterestRate.setEnabled(true);
                btnCancel.setEnabled(true);
                btnSave.setEnabled(true);
            }
        }));
    }

    // disables all text fields when the activity becomes active
    private void initDisable(boolean isEnabled){
        etAccountNumber.setEnabled(isEnabled);
        etInitBalance.setEnabled(isEnabled);
        etCurrentBalance.setEnabled(isEnabled);
        etPaymentAmount.setEnabled(isEnabled);
        etInterestRate.setEnabled(isEnabled);
        btnCancel.setEnabled(isEnabled);
        btnSave.setEnabled(isEnabled);
    }

    /* this method is called when the save is clicked
    * it saves the the current data in the table */
    private void initSaveButton(){

        btnSave.setOnClickListener(v -> {

            boolean wasSuccessful;

            // instantiates financeDataSource object
            FinancesDataSource ds = new FinancesDataSource(MainActivity.this);

            if (radCDs.isChecked()){
                try {
                    ds.open(); // open the database
                    wasSuccessful = ds.insertCDs(cDs); // saves the data and returns a value and sets it to wasSuccessful

                    /* if the returned value is true make a toast and call clearTextFields() to clear all fields */
                    if (wasSuccessful){
                        Toast.makeText(getApplicationContext(), "Successfully Saved!" , Toast.LENGTH_SHORT).show();
                        clearTextFields(); // called to clear all text fields
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Failed to save!" , Toast.LENGTH_SHORT).show();

                    ds.close(); // close the database
                }
                catch (Exception e){
                    // do nothing when exception is caught
                }
            }
            else if (radChecking.isChecked()){
                try {
                    ds.open(); // open the database
                    wasSuccessful = ds.insertChecking(checkingAccount); // saves the data and returns a value and sets it to wasSuccessful

                    /* if the returned value is true make a toast and call clearTextFields() to clear all fields */
                    if (wasSuccessful){
                        Toast.makeText(getApplicationContext(), "Successfully Saved!" , Toast.LENGTH_SHORT).show();
                        clearTextFields(); // called to clear all text fields
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Failed to save!" , Toast.LENGTH_SHORT).show();

                    ds.close(); // close the database
                }
                catch (Exception e){
                    // do nothing when exception is caught
                }
            }
            else if (radLoans.isChecked()){
                try {
                    ds.open(); // open the database
                    wasSuccessful = ds.insertLoans(loans); // saves the data and returns a value and sets it to wasSuccessful

                    /* if the returned value is true make a toast and call clearTextFields() to clear all fields */
                    if (wasSuccessful){
                        Toast.makeText(getApplicationContext(), "Successfully Saved!" , Toast.LENGTH_SHORT).show();
                        clearTextFields(); // called to clear all text fields
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Failed to save!" , Toast.LENGTH_SHORT).show();

                    ds.close(); // close the database
                }
                catch (Exception e){
                    // do nothing when exception is caught
                }
            }
        });
    }

    /* this method is called when the cancel button is clicked and
    * it clear the text fields and do not save the current information */
    private void initCancelButton(){
        btnCancel.setOnClickListener(v -> {
            if (radCDs.isChecked()){
                clearTextFields();
                Toast.makeText(getApplicationContext(), "Not Saved!" , Toast.LENGTH_SHORT).show();
            }
            if (radChecking.isChecked()){
                clearTextFields();
                Toast.makeText(getApplicationContext(), "Not Saved!" , Toast.LENGTH_SHORT).show();
            }
            if (radLoans.isChecked()){
                clearTextFields();
                Toast.makeText(getApplicationContext(), "Not Saved!" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* clears the text fields, uses multithreading sleep method
    to delay the process so that is be able to clear all of them */
    private void clearTextFields(){
        try {
            etAccountNumber.setText("");
            Thread.sleep(1); // waits 1 millisecond before the next process
            etAccountNumber.requestFocus();
        }
        catch (Exception e){ /* do nothing */ }

        try {
            etInitBalance.setText("");
            Thread.sleep(1); // waits 1 millisecond before the next process
        }
        catch (Exception e){ /* do nothing */ }

        try {
            etCurrentBalance.setText("");
            Thread.sleep(1); // waits 1 millisecond before the next process
        }
        catch (Exception e){ /* do nothing */ }

        try {
            etInterestRate.setText("");
            Thread.sleep(1); // waits 1 millisecond before the next process
        }
        catch (Exception e){ /* do nothing */ }
        try {
            etPaymentAmount.setText("");
            Thread.sleep(1); // waits 1 millisecond before the next process
        }
        catch (Exception e){ /* do nothing */ }
    }

    /* this method monitors and change in the text field text,
    * and it is triggered if there is a change*/
    private void initTextChangedEvent(){
        etAccountNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (radCDs.isChecked())
                    cDs.setAccountNumber(Integer.parseInt(etAccountNumber.getText().toString()));
                if (radLoans.isChecked())
                    loans.setAccountNumber(Integer.parseInt(etAccountNumber.getText().toString()));
                if (radChecking.isChecked())
                    checkingAccount.setAccountNumber(Integer.parseInt(etAccountNumber.getText().toString()));
            }
        });

        etInitBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (radCDs.isChecked())
                    cDs.setInitBalance(Double.parseDouble(etInitBalance.getText().toString()));
                if (radLoans.isChecked())
                    loans.setInitBalance(Double.parseDouble(etInitBalance.getText().toString()));
            }
        });

        etCurrentBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (radCDs.isChecked())
                    cDs.setCurrentBalance(Double.parseDouble(etCurrentBalance.getText().toString()));
                if (radLoans.isChecked())
                    loans.setCurrentBalance(Double.parseDouble(etCurrentBalance.getText().toString()));
                if (radChecking.isChecked())
                    checkingAccount.setCurrentBalance(Double.parseDouble(etCurrentBalance.getText().toString()));
            }
        });

        etPaymentAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                loans.setPaymentAmount(Double.parseDouble(etPaymentAmount.getText().toString()));
            }
        });

        etInterestRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (radCDs.isChecked())
                    cDs.setInterestRate(Double.parseDouble(etInterestRate.getText().toString()));
                if (radLoans.isChecked())
                    loans.setInterestRate(Double.parseDouble(etInterestRate.getText().toString()));
            }
        });
    }
}