package com.alistair.hotspots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RateDialog.SaveRatingBarListener {

    Rate currentRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentRate = new Rate(); // instantiate rate object
        initRateButton(); // call to open rate activity
        initSaveButton(); // call to save bar details
        initTextChangedEvent(); // call to monitor a change in the text fields
    }


    /* this method passes the ratings values from the rate custom dialog
     * to be used in the main activity when the dialog is dismissed */
    @SuppressLint("SetTextI18n")
    @Override
    public void didFinishRating(float beer, float wine, float music) {
        TextView textView = findViewById(R.id.textRating);
        float averageRatings = (beer + wine + music) / 3;
        averageRatings = Math.round(averageRatings * 10.0f) / 10.0f;
        textView.setText("" + averageRatings + " / 5"); // sets average ratings

        // use the values received from the rate dialog to set the object values
        currentRate.setBeerRatings(beer);
        currentRate.setWineRatings(wine);
        currentRate.setMusicRatings(music);
        currentRate.setAverageRatings(beer, wine, music);
    }

    // opens rate custom dialog to rate beer, wine and music
    private void initRateButton(){
        Button btnRate = findViewById(R.id.buttonRate);

        btnRate.setOnClickListener(v ->{
            FragmentManager fm = getSupportFragmentManager();
            RateDialog rateDialog = new RateDialog();
            rateDialog.show(fm, "Rate");
        });
    }

    /* this method save all the bar details and ratings when the save button is clicked */
    @SuppressLint("SetTextI18n")
    private void initSaveButton(){
        // instantiates layout widgets objects
        EditText barName = findViewById(R.id.editName);
        EditText address = findViewById(R.id.editAddress);
        EditText city = findViewById(R.id.editCity);
        EditText state = findViewById(R.id.editState);
        EditText zipcode = findViewById(R.id.editZipcode);
        TextView ratings = findViewById(R.id.textRating);
        Button btnSave = findViewById(R.id.buttonSaveRating);

        btnSave.setOnClickListener(v -> {
            boolean wasSuccessful;

            RateDataSource ds = new RateDataSource(MainActivity.this);

            try {
                ds.open(); // opens the database
                if (currentRate.get_id() == -1){
                    wasSuccessful = ds.insertData(currentRate); // saves the data and return a boolean value

                    if (wasSuccessful) { // if true display a short message and clear all fields
                        Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                        barName.setText("");
                        address.setText("");
                        city.setText("");
                        state.setText("");
                        zipcode.setText("");
                        ratings.setText("No ratings!");
                        barName.requestFocus();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Failed to saved!" , Toast.LENGTH_SHORT).show();
                }
                ds.close(); // close the database
            }
            catch (Exception e){
                // do nothing when the exception is caught
            }
        });
    }

    // this method monitor a change in text fields and pass the text to their respective objects
    private void initTextChangedEvent(){
        EditText barName = findViewById(R.id.editName);
        barName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                currentRate.setBarName(barName.getText().toString());
            }
        });

        EditText address = findViewById(R.id.editAddress);
        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                currentRate.setAddress(address.getText().toString());
            }
        });

        EditText city = findViewById(R.id.editCity);
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                currentRate.setCity(city.getText().toString());
            }
        });

        EditText state = findViewById(R.id.editState);
        state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentRate.setState(state.getText().toString());
            }
        });

        EditText zipcode = findViewById(R.id.editZipcode);
        zipcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentRate.setZipcode(Integer.parseInt(zipcode.getText().toString()));
            }
        });
    }
}