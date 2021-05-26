package com.alistair.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import android.text.format.*;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener, RelationshipPicker.SaveRadioListener {

    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // nav buttons methods to activate the activities
        initListButton();
        initMapButton();
        initSettingsButton();

        // toolbar buttons methods
        initToggleButton();
        setForEditing(false);

        initSaveButton(); // method call to save a new contact
        setRelationship(); // method call to set a contact's relationship
        initChangeDateButton(); // method call to change date

        currentContact = new Contact(); // contact object
    }

    // opens contact list activity
    private void initListButton(){
        ImageButton ibList = findViewById(R.id.imageButtonList);

        ibList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // opens map activity
    private void initMapButton(){
        ImageButton ibMap = findViewById(R.id.imageButtonMap);

        ibMap.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactMapActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // opens settings activity
    private void initSettingsButton(){
        ImageButton ibSettings = findViewById(R.id.imageButtonSettings);
        ibSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // toggles on and off button
    private void initToggleButton(){
        final ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
        editToggle.setOnClickListener(v ->{
            setForEditing(editToggle.isChecked());
        });
    }

    // enables and disabled the widgets for editing
    private void setForEditing(boolean enabled){
        // instantiates layout widgets objects
        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipcode = findViewById(R.id.editZipcode);
        EditText editPhone = findViewById(R.id.editHome);
        EditText editCell = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEmail);
        Button buttonChange = findViewById(R.id.btnBirthday);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonSetRelationship = findViewById(R.id.buttonSetRelationship);

        // enable of disables widgets
        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipcode.setEnabled(enabled);
        editPhone.setEnabled(enabled);
        editCell.setEnabled(enabled);
        editEmail.setEnabled(enabled);
        buttonChange.setEnabled(enabled);
        buttonSave.setEnabled(enabled);
        buttonSetRelationship.setEnabled(enabled);

        if(enabled)
            editName.requestFocus();
        else{
            ScrollView s = findViewById(R.id.scrollView);
            s.fullScroll(ScrollView.FOCUS_UP);
        }
    }

    // receives the selected date from the custom date dialog
    // and displays the date of birth before the dialog closes
    @Override
    public void didFinishDatePickerDialog(Calendar selectedTime) {
        TextView birthDay = findViewById(R.id.textBirthday);

        // displays the date of birth
        birthDay.setText(DateFormat.format("MM/dd/yyyy",selectedTime));
        currentContact.setBirthday(selectedTime);
    }

    // opens custom date dialog for picking
    private void initChangeDateButton(){
        Button changeDate = findViewById(R.id.btnBirthday);

        changeDate.setOnClickListener(v ->{
            FragmentManager fm = getSupportFragmentManager();
            DatePickerDialog datePickerDialog = new DatePickerDialog();
            datePickerDialog.show(fm, "DatePick");
        });
    }

    // saves the current contact into the database
    private void initSaveButton(){
        Button saveButton = findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(v -> {
            hideKeyboard();
            boolean wasSuccessful = false;

            // data source object to pass data to save into the database
            ContactDataSource ds = new ContactDataSource(MainActivity.this);
            try {
                ds.open(); // open database connection

                if (currentContact.getContactID() == -1) {
                    wasSuccessful = ds.insertContact(currentContact);
                    initSetContactDetails(); // method call to set the contact details

                    if (wasSuccessful){
                        int newId = ds.getLastContactID();
                        currentContact.setContactID(newId);
                        Toast.makeText(getApplicationContext(), "Successfully saved!", Toast.LENGTH_SHORT ).show();
                    }
                }
                // updates the address if address values were changed
                else if(didChangeAddress() && ds.getLastContactID() > -1){
                    wasSuccessful = ds.updateAddress(currentContact);

                    Toast.makeText(getApplicationContext(), "Address was updated!", Toast.LENGTH_SHORT).show();
                }
                else{
                    wasSuccessful = ds.updateContact(currentContact);

                    Toast.makeText(getApplicationContext(), "Contact was updated!", Toast.LENGTH_SHORT).show();
                }

                ds.close(); // close database connection
            }
            catch (Exception e){
                wasSuccessful = false;
            }

            if (wasSuccessful){
                ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
                editToggle.toggle();
                setForEditing(false); // disable text fields and the buttons
            }
        });
    }

    // this method monitors a change in text and assigns the new value
    private void initSetContactDetails(){
        final EditText etContactName = findViewById(R.id.editName);
        final EditText etStreetAddress = findViewById(R.id.editAddress);
        final EditText etCity = findViewById(R.id.editCity);
        final EditText etState = findViewById(R.id.editState);
        final EditText etZipcode = findViewById(R.id.editZipcode);
        final EditText etPhoneNumber = findViewById(R.id.editHome);
        final EditText etCellPhone = findViewById(R.id.editCell);
        final EditText etEmail = findViewById(R.id.editEmail);

        // set contact name from the edit text widget
        currentContact.setContactName(etContactName.getText().toString());
        // set street address from the edit text widget
        currentContact.setStreetAddress(etStreetAddress.getText().toString());
        // set city from the edit text widget
        currentContact.setCity(etCity.getText().toString());
        // set state from the edit text widget
        currentContact.setState(etState.getText().toString());
        // set zipcode from the edit text widget
        currentContact.setZipcode(etZipcode.getText().toString());
        // set phone number from the edit text widget
        currentContact.setPhoneNumber(etPhoneNumber.getText().toString());
        // set cell phone from the edit text widget
        currentContact.setCellNumber(etCellPhone.getText().toString());
        // set email from the edit text widget
        currentContact.setEmail(etEmail.getText().toString());
    }

    // update address only. returns true is address was changed and sets new values
    private boolean didChangeAddress(){
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipcode = findViewById(R.id.editZipcode);

        // if any of the edit text was modified set new values
        // and set addressDidChange to true
        if(!currentContact.getStreetAddress().equalsIgnoreCase(editAddress.getText().toString()) ||
                !currentContact.getCity().equalsIgnoreCase(editCity.getText().toString()) ||
                !currentContact.getState().equalsIgnoreCase(editState.getText().toString()) ||
                !currentContact.getZipcode().equalsIgnoreCase(editZipcode.getText().toString()))
        {
            // set new values
            currentContact.setStreetAddress(editAddress.getText().toString());
            currentContact.setCity(editCity.getText().toString());
            currentContact.setState(editState.getText().toString());
            currentContact.setZipcode(editZipcode.getText().toString());

            // return true
            return true;
        }
        else
            return false;
    }

    // opens a custom dialog for setting the relationship
    private void setRelationship(){
        Button setRelationship = findViewById(R.id.buttonSetRelationship);

        setRelationship.setOnClickListener(v ->{
            FragmentManager fm = getSupportFragmentManager();
            RelationshipPicker datePickerDialog = new RelationshipPicker();
            datePickerDialog.show(fm, "Select Relationship");
        });
    }

    // called when relationship custom dialog is closed
    // and sets the new relationship returned
    @Override
    public void didFinishSetting(String s) {
        TextView birthDay = findViewById(R.id.textRelationship);
        birthDay.setText(s);
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipcode = findViewById(R.id.editZipcode);
        EditText editPhone = findViewById(R.id.editHome);
        EditText editCell = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEmail);

        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editCity.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editState.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editZipcode.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editPhone.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editCell.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editEmail.getWindowToken(), 0);
    }
}