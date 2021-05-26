package com.alistair.supermarket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RatingActivity extends AppCompatActivity {

    private Supermarket currentData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        currentData = new Supermarket(); // instantiate the object

        initMainActivity(); // call to open the main activity
        initSaveButton(); // call to save the supermarket ratings
    }

    // opens the main activity
    private void initMainActivity(){
        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(RatingActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // saves data into the database when the save button is clicked
    @SuppressLint("SetTextI18n")
    private void initSaveButton(){
        // instantiate widget objects
        Button buttonSaveRatings = findViewById(R.id.buttonSaveRatings);
        RatingBar rbLiquor = findViewById(R.id.rbLiquor);
        RatingBar rbProduce = findViewById(R.id.rbProduce);
        RatingBar rbMeat = findViewById(R.id.rbMeat);
        RatingBar rbCheese = findViewById(R.id.rbCheese);
        RatingBar rbEaseOfCheckout = findViewById(R.id.rbEase);
        TextView tvRatings = findViewById(R.id.textAverageRatings);

        buttonSaveRatings.setOnClickListener(v -> {
            boolean wasSuccessful;

            // get values from the layout widgets and sets them
            currentData.setLiquorRating(rbLiquor.getRating());
            currentData.setProduceRating(rbProduce.getRating());
            currentData.setMeatRating(rbMeat.getRating());
            currentData.setCheeseRating(rbCheese.getRating());
            currentData.setEaseOfCheckoutRating(rbEaseOfCheckout.getRating());
            currentData.setAverageRating(rbLiquor.getRating(),rbProduce.getRating(),
                    rbMeat.getRating(),rbCheese.getRating(),rbEaseOfCheckout.getRating());

            SupermarketDataSource ds = new SupermarketDataSource(RatingActivity.this);
            try {
                ds.open(); // open the database
                wasSuccessful = ds.insertRatingsData(currentData);

                if (wasSuccessful) {
                    // display the average ratings on the rating activity
                    tvRatings.setText("" + currentData.getAverageRating());
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
