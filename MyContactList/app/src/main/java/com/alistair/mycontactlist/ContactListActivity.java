package com.alistair.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        initMapButton(); // method call to open map activity
        initSettingsButton(); // method call to open settings activity
        initListButton(); // method call to disable contact list button
    }

    // disables the contact list image button when the activity is active
    private void initListButton(){
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setEnabled(false);
    }

    // opens map activity
    private void initMapButton(){
        ImageButton ibList = findViewById(R.id.imageButtonMap);
        ibList.setOnClickListener(v -> {
            Intent intent = new Intent(ContactListActivity.this, ContactMapActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    // opens settings activity
    private void initSettingsButton(){
        ImageButton ibList = findViewById(R.id.imageButtonSettings);
        ibList.setOnClickListener(v -> {
            Intent intent = new Intent(ContactListActivity.this, ContactSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}