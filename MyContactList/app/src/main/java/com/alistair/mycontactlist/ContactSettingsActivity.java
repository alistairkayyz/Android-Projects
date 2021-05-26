package com.alistair.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

public class ContactSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);

        // navigation menu buttons
        initListButton();
        initMapButton();
        initSettingsButton();

        // settings method calls to initialize settings
        initSettings();
        initSortByClick();
        initSortOrderClick();

        // background color settings method calls
        initBackgroundColorSettings();
        initBackgroundColorClick();
    }

    // disables settings image button
    private void initSettingsButton(){
        ImageButton ibSettings = findViewById(R.id.imageButtonSettings);
        ibSettings.setEnabled(false);
    }

    // opens contact list activity
    private void initListButton(){
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(v -> {
            Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // opens map activity
    private void initMapButton(){
        ImageButton ibList = findViewById(R.id.imageButtonMap);
        ibList.setOnClickListener(v -> {
            Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // this method keep initializes the last checked radio button
    private void initSettings(){
        // get the persistent data store in the sharedPreferences
        String sortBy = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortfield", "contactname");

        String sortOrder = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortorder", "ASC");

        RadioButton rbName = findViewById(R.id.radioName);
        RadioButton rbCity = findViewById(R.id.radioCity);
        RadioButton rbBirthDay = findViewById(R.id.radioBirthday);

        RadioButton rbAscending = findViewById(R.id.radioAscending);
        RadioButton rbDescending = findViewById(R.id.radioDescending);

        // checks the last checked radio button when the activity is active
        if(sortBy.equalsIgnoreCase("contactname"))
            rbName.setChecked(true);
        else if (sortBy.equalsIgnoreCase("city"))
            rbCity.setChecked(true);
        else
            rbBirthDay.setChecked(true);

        // check the last checked radio button when the activity is active
        if (sortOrder.equalsIgnoreCase("ASC"))
            rbAscending.setChecked(true);
        else
            rbDescending.setChecked(true);

    }

    // this method keeps the last checked radio button checked
    private void initSortByClick(){
        RadioGroup rgSortBy = findViewById(R.id.radioGroudSortBy);

        rgSortBy.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton rbName = findViewById(R.id.radioName);
            RadioButton rbCity = findViewById(R.id.radioCity);

            // store the last checked radio button value in the sharedPreference
            // when a radio button is checked and when the activity is active
            if (rbName.isChecked())
                getSharedPreferences("MyContactListPreferences",
                        Context.MODE_PRIVATE).edit().putString("sortfield", "contactname").apply();

            else if (rbCity.isChecked())
                getSharedPreferences("MyContactListPreferences",
                        Context.MODE_PRIVATE).edit().putString("sortfield", "city").apply();

            else
                getSharedPreferences("MyContactListPreferences",
                        Context.MODE_PRIVATE).edit().putString("sortfield", "birthday").apply();
        });
    }

    // this method keeps the last checked radio button checked
    private void initSortOrderClick(){
        RadioGroup rgSortOrder = findViewById(R.id.radioGroupSortOrder);
        rgSortOrder.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rbAscending = findViewById(R.id.radioAscending);

            // store the last checked radio button value in the sharedPreference
            // when a radio button is checked and when the activity is active
            if (rbAscending.isChecked())
                getSharedPreferences("MyContactListPreferences",
                        Context.MODE_PRIVATE).edit().putString("sortorder","ASC").apply();
            else
                getSharedPreferences("MyContactListPreferences",
                        Context.MODE_PRIVATE).edit().putString("sortorder","DESC").apply();
        });
    }

    // this method retrieves and sets the last set background color
    private void initBackgroundColorSettings(){
        ScrollView scrollViewObject = findViewById(R.id.scrollView2);

        // set default background color to iceberg if it is not set
        String backgroundColor = getSharedPreferences("MyBackgroundColorPreferences",
                Context.MODE_PRIVATE).getString("backgroundcolor", "iceberg");

        RadioButton paleGold = findViewById(R.id.radioPaleGold);
        RadioButton hawkesBlue = findViewById(R.id.radioHawkesBlue);
        RadioButton moonstoneBlue = findViewById(R.id.radioMoonstoneBlue);
        RadioButton iceberg = findViewById(R.id.radioIceberg);
        RadioButton offGreen = findViewById(R.id.radioOffGreen);
        RadioButton dawnPink = findViewById(R.id.radioDawnPink);

        // remembers the last set background color and keeps it active
        if(backgroundColor.equalsIgnoreCase("palegold")){
            paleGold.setChecked(true);
            scrollViewObject.setBackgroundResource(R.color.bg_pale_gold);
        }
        else if (backgroundColor.equalsIgnoreCase("hawkesblue")) {
            hawkesBlue.setChecked(true);
            scrollViewObject.setBackgroundResource(R.color.bg_hawkes_blue);
        }
        else if (backgroundColor.equalsIgnoreCase("moonstoneblue")) {
            moonstoneBlue.setChecked(true);
            scrollViewObject.setBackgroundResource(R.color.bg_moonstone_blue);
        }
        else if (backgroundColor.equalsIgnoreCase("iceberg")) {
            iceberg.setChecked(true);
            scrollViewObject.setBackgroundResource(R.color.bg_iceberg);
        }
        else if (backgroundColor.equalsIgnoreCase("offgreen")) {
            offGreen.setChecked(true);
            scrollViewObject.setBackgroundResource(R.color.bg_off_green);
        }
        else {
            dawnPink.setChecked(true);
            scrollViewObject.setBackgroundResource(R.color.bg_pale_gold);
        }

    }

    // this method sets a new background color when the appropriate radio button is checked
    private void initBackgroundColorClick(){
        ScrollView scrollViewObject = findViewById(R.id.scrollView2);

        RadioGroup rgBackgroundColors = findViewById(R.id.rgBackfroundColors);

        rgBackgroundColors.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton paleGold = findViewById(R.id.radioPaleGold);
            RadioButton hawkesBlue = findViewById(R.id.radioHawkesBlue);
            RadioButton moonstoneBlue = findViewById(R.id.radioMoonstoneBlue);
            RadioButton iceberg = findViewById(R.id.radioIceberg);
            RadioButton offGreen = findViewById(R.id.radioOffGreen);
            RadioButton dawnPink = findViewById(R.id.radioDawnPink);

            // sets a new background color using the scrollViewObject
            if (paleGold.isChecked()) {
                getSharedPreferences("MyBackgroundColorPreferences",
                        Context.MODE_PRIVATE).edit().putString("backgroundcolor", "palegold").apply();
                scrollViewObject.setBackgroundResource(R.color.bg_pale_gold);
            }

            else if (hawkesBlue.isChecked()) {
                getSharedPreferences("MyBackgroundColorPreferences",
                        Context.MODE_PRIVATE).edit().putString("backgroundcolor", "hawkesblue").apply();
                scrollViewObject.setBackgroundResource(R.color.bg_hawkes_blue);
            }

            else if (moonstoneBlue.isChecked()) {
                getSharedPreferences("MyBackgroundColorPreferences",
                        Context.MODE_PRIVATE).edit().putString("backgroundcolor", "moonstoneblue").apply();
                scrollViewObject.setBackgroundResource(R.color.bg_moonstone_blue);
            }

            else if (iceberg.isChecked()) {
                getSharedPreferences("MyBackgroundColorPreferences",
                        Context.MODE_PRIVATE).edit().putString("backgroundcolor", "iceberg").apply();
                scrollViewObject.setBackgroundResource(R.color.bg_iceberg);
            }

            else if (offGreen.isChecked()) {
                getSharedPreferences("MyBackgroundColorPreferences",
                        Context.MODE_PRIVATE).edit().putString("backgroundcolor", "offgreen").apply();
                scrollViewObject.setBackgroundResource(R.color.bg_off_green);
            }

            else if (dawnPink.isChecked()) {
                getSharedPreferences("MyBackgroundColorPreferences",
                        Context.MODE_PRIVATE).edit().putString("backgroundcolor", "dawnpink").apply();
                scrollViewObject.setBackgroundResource(R.color.bg_dawn_pink);
            }

        });
    }
}