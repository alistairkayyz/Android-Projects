 package com.alistair.mealrater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RateMeal.SaveRatingBarListener{
    private MealRate currentMealRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRateMealButton(); // method call to open rate dialog
        initSaveButton(); // method call to save meal ratings
        initTextChangedEvents(); // method call to monitor a text change

        currentMealRate = new MealRate(); // instantiates mealRate object
    }

    // opens meal rate custom dialog
    private void initRateMealButton(){
        Button mealRate = findViewById(R.id.buttonRate);

        mealRate.setOnClickListener(v ->{
            FragmentManager fm = getSupportFragmentManager();
            RateMeal rateMeal = new RateMeal();
            rateMeal.show(fm, "Rate");
        });
    }

    /* this method receives results from the custom dialog displays the ratings
     just before the dialog closes when the save button is clicked */
    @Override
    public void didFinishRating(String rb) {
        TextView textRating = findViewById(R.id.textRating);
        textRating.setText(rb);
    }

    // this method save the meal details and ratings into the database
    // when the save button is clicked
    private void initSaveButton(){
        // instantiates layout widget objects
        Button saveButton = findViewById(R.id.buttonSaveMealRating);
        Button rate = findViewById(R.id.buttonRate);
        EditText res = findViewById(R.id.editRestaurant);
        EditText meal = findViewById(R.id.editMeal);

        saveButton.setOnClickListener(v -> {
            boolean wasSuccessful;

            MealRateDataSource ds = new MealRateDataSource(MainActivity.this);
            try {
                ds.open(); // opens the database
                if (currentMealRate.getMealRateID() == -1) {
                    // saves the data and return a boolean value
                    wasSuccessful = ds.insertData(currentMealRate);

                    // disables the text fields and displays a message if it succeeded
                    if (wasSuccessful){
                        rate.setEnabled(false);
                        res.setEnabled(false);
                        meal.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "Successfully Saved!" , Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    wasSuccessful = ds.updateData(currentMealRate);

                ds.close(); // close the database
            }
            catch (Exception e){
               // wasSuccessful = false;
            }
        });
    }

    private void initTextChangedEvents(){
        final EditText etRestaurant = findViewById(R.id.editRestaurant);
        etRestaurant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                currentMealRate.setRestaurant(etRestaurant.getText().toString());
            }
        });

        final EditText etMeal = findViewById(R.id.editMeal);
        etMeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                currentMealRate.setMeal(etMeal.getText().toString());
            }
        });

        final TextView tvRatings = findViewById(R.id.textRating);
        tvRatings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                currentMealRate.setRatings(tvRatings.getText().toString());
            }
        });
    }
}