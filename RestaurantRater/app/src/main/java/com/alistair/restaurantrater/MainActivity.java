package com.alistair.restaurantrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Restaurant currentRestaurant;
    private int restaurantID = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentRestaurant = new Restaurant();
        initRateDishActivity(); // call to open rate dish activity
        initSaveButton(); // call to save restaurant details into the database
    }

    // opens the rate dish activity when the button is clicked
    private void initRateDishActivity(){
        Button buttonRate = findViewById(R.id.buttonRateDish);

        buttonRate.setOnClickListener(v -> {
            // opens the rate dish activity if the current details are saved
            if (restaurantID >= 0){
                Intent intent = new Intent(MainActivity.this, RateDishActivity.class);
                intent.putExtra("restaurantId", restaurantID);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else
                Toast.makeText(getApplicationContext(), "Please Save Current Data!", Toast.LENGTH_SHORT).show();
        });
    }

    // saves the details into the database when the button is clicked
    private void initSaveButton(){
        // instantiates layout widgets objects
        Button buttonSaveDetails = findViewById(R.id.buttonSaveDetails);
        EditText etAddress = findViewById(R.id.editAddress);
        EditText etRestaurantName = findViewById(R.id.editName);
        EditText etCity = findViewById(R.id.editCity);
        EditText etState = findViewById(R.id.editState);
        EditText etZipCode = findViewById(R.id.editZipcode);

        buttonSaveDetails.setOnClickListener(v -> {
            boolean wasSuccessful;

            // get widgets text and sets it to their respective class objects
            currentRestaurant.setRestaurantName(etRestaurantName.getText().toString());
            currentRestaurant.setStreetAddress(etAddress.getText().toString());
            currentRestaurant.setCity(etCity.getText().toString());
            currentRestaurant.setState(etState.getText().toString());
            currentRestaurant.setZipCode(Short.parseShort(etZipCode.getText().toString()));

            // instantiates the restaurant data source object
            RestaurantDataSource ds = new RestaurantDataSource(MainActivity.this);
            try {
                ds.open(); // open the database

                // saves the data into the database and returns true then assigns it to wasSuccessful or false otherwise
                wasSuccessful = ds.insertRestaurantData(currentRestaurant);

                if (wasSuccessful) {
                    restaurantID = ds.getLastRestaurantId(); // gets the id of the new row of data
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