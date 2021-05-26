package com.alistair.tip_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button tenPercent;
    Button fifteenPercent;
    Button twentyPercent;
    Button twentyFivePercent;
    EditText editText;
    TextView textView;
    double money;
    double finalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tenPercent = findViewById(R.id.tenPercent);
        fifteenPercent = findViewById(R.id.fiftennPercent);
        twentyPercent = findViewById(R.id.twentyPercent);
        twentyFivePercent = findViewById(R.id.twentyFivePercent);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView2);

        finalAmount = 1.00;

        tenPercent.setOnClickListener(this);
        fifteenPercent.setOnClickListener(this);
        twentyPercent.setOnClickListener(this);
        twentyFivePercent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        money = Double.parseDouble(editText.getText().toString());

        if(v.getId() == tenPercent.getId()){
            finalAmount = money * .10;
            finalAmount = Math.round(finalAmount * 100.0)/100.0;
            textView.setText("R" + finalAmount);
        }
        if(v.getId() == fifteenPercent.getId()){
            finalAmount = money * .15;
            finalAmount = Math.round(finalAmount * 100.0)/100.0;
            textView.setText("R" + finalAmount);
        }
        if(v.getId() == twentyPercent.getId()){
            finalAmount = money * .20;
            finalAmount = Math.round(finalAmount * 100.0)/100.0;
            textView.setText("R" + finalAmount);
        }
        if(v.getId() == twentyFivePercent.getId()){
            finalAmount = money * .25;
            finalAmount = Math.round(finalAmount * 100.0)/100.0;
            textView.setText("R" + finalAmount);
        }
    }
}