package com.alistair.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class ContactMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_map);

        initMapButton(); // method call to disable map activity
        initSettingsButton(); // method call to open settings activity
        initListButton(); // method call to open contact list button
    }

    // disables map image button when the activity activates
    private void initMapButton(){
        ImageButton ibMap = findViewById(R.id.imageButtonMap);
        ibMap.setEnabled(false);
    }

    // opens contact list activity
    private void initListButton(){
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(v -> {
            Intent intent = new Intent(ContactMapActivity.this, ContactListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // opens settings activity
    private void initSettingsButton(){
        ImageButton ibList = findViewById(R.id.imageButtonSettings);
        ibList.setOnClickListener(v -> {
            Intent intent = new Intent(ContactMapActivity.this, ContactSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}