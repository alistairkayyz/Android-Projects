package com.alistair.restaurantrater;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RateDishActivity extends AppCompatActivity {
    private Dish currentDish;
    private int restaurantId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_dish);

        currentDish = new Dish(); // instantiates the dish object
        initMainActivity(); // call to return to main activity
        initSaveButton(); // call to save the ratings

    }

    // opens the main activity when the back button id clicked
    private void initMainActivity(){
        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(RateDishActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // save the ratings into the database when the button is clicked
    private void initSaveButton(){
        // instantiates the layout widgets objects
        Button buttonSaveDetails = findViewById(R.id.buttonSaveRatings);
        EditText etDishName = findViewById(R.id.editDishName);
        EditText etDishType = findViewById(R.id.editDishType);
        RatingBar ratingBar = findViewById(R.id.ratingBarRatings);

        buttonSaveDetails.setOnClickListener(v -> {
            boolean wasSuccessful;

            // get restaurantId passed from the main activity
            Bundle ex = getIntent().getExtras();
            if (ex!=null){
                restaurantId = ex.getInt("restaurantId");
            }

            // gets the text from the widgets and pass it to their respective objects
            currentDish.setDishName(etDishName.getText().toString());
            currentDish.setDishType(etDishType.getText().toString());
            currentDish.setRating(ratingBar.getRating());
            currentDish.setRestaurantId(restaurantId);

            // instantiates the data source object
            RestaurantDataSource ds = new RestaurantDataSource(RateDishActivity.this);
            try {
                ds.open(); // open the database
                // saves the data and return true if it succeeds or false otherwise
                wasSuccessful = ds.insertDishData(currentDish);

                if (wasSuccessful) {
                    Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Failed to save!", Toast.LENGTH_SHORT).show();

                ds.close();
            }
            catch (Exception e){
                // wasSuccessful = false;
            }
        });
    }
}
