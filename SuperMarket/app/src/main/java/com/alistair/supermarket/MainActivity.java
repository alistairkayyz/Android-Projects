package com.alistair.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Supermarket currentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentData = new Supermarket(); // instantiate the object

        initRatingActivity(); // call to open the rating activity
        initSaveButton(); // call to save the supermarket details
    }

    // opens the rating activity
    private void initRatingActivity(){
        Button buttonRate = findViewById(R.id.buttonRate);
        buttonRate.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RatingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // saves data into the database when the save button is clicked
    private void initSaveButton(){
        // instantiate widgets objects
        Button buttonSaveDetails = findViewById(R.id.buttonSaveDetails);
        EditText etAddress = findViewById(R.id.editSupermarketAddress);
        EditText etSupermarketName = findViewById(R.id.editSupermarketName);
        EditText etCity = findViewById(R.id.editCity);
        EditText etState = findViewById(R.id.editState);
        EditText etZipcode = findViewById(R.id.editZipcode);

        buttonSaveDetails.setOnClickListener(v -> {
            boolean wasSuccessful;

            // get values from the layout widgets and sets them
            currentData.setSuperMarketName(etSupermarketName.getText().toString());
            currentData.setAddress(etAddress.getText().toString());
            currentData.setCity(etCity.getText().toString());
            currentData.setState(etState.getText().toString());
            currentData.setZipcode(Integer.parseInt(etZipcode.getText().toString()));

            // instantiate data source object
            SupermarketDataSource ds = new SupermarketDataSource(MainActivity.this);
            try {
                ds.open(); // opens the database
                wasSuccessful = ds.insertDetailsData(currentData); // saves data into the database and return a value

                if (wasSuccessful) {
                    Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Failed to save!", Toast.LENGTH_SHORT).show();

                ds.close(); // close the database
            }
            catch (Exception e){
                // wasSuccessful = false;
            }
        });
    }
}