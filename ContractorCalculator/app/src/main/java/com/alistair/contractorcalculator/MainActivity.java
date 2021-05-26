package com.alistair.contractorcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements TaxRateSetter.SaveTaxRateListener{

    double TAX_RATE;
    double labor;
    double materialCost;
    double subtotal;
    double tax;
    double total;
    TextView tvTaxRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTaxRate(); // call to retrieve the latest tax rate
        initChangeTaxRateButton(); //  call to open tax rate dialog
        calculatorCost(); // call to calculate the total cost
    }

    /* this method calculates the total cost using labor cost, material cost and
     * the tax rate provided */
    @SuppressLint("SetTextI18n")
    private void calculatorCost(){
        // instantiates layout widget objects
        Button calculate = findViewById(R.id.buttonCalculate);
        EditText etLabor = findViewById(R.id.editLabor);
        EditText etMaterialCost = findViewById(R.id.editMaterialcost);
        TextView tvSubtotal = findViewById(R.id.textSubtotal);
        TextView tvTax = findViewById(R.id.textTax);
        TextView tvTotal = findViewById(R.id.textTotal);

        calculate.setOnClickListener(v -> {
            // retrieves the cost values from the edit texts
            labor = Double.parseDouble(etLabor.getText().toString());
            materialCost = Double.parseDouble(etMaterialCost.getText().toString());

            subtotal = labor + materialCost; // calculate sub total
            tax = subtotal * TAX_RATE; // calculate tax amount
            total = subtotal + tax; // calculate total cost

            // round up to 1 or 2 decimal places
            subtotal = Math.round(subtotal * 100.0)/100.0;
            tax = Math.round(tax * 100.0)/100.0;
            total = Math.round(total * 100.0)/100.0;

            tvSubtotal.setText("R" + subtotal); // displays the sub total
            tvTax.setText("R" + tax); // displays the tax amount
            tvTotal.setText("R" + total); // displays the total cost
        });
    }

    // opens tax rate custom dialog
    private void initChangeTaxRateButton(){
        Button changeTaxRate = findViewById(R.id.buttonChangeRate);

        changeTaxRate.setOnClickListener(v ->{
            FragmentManager fm = getSupportFragmentManager();
            TaxRateSetter taxRateSetter = new TaxRateSetter();
            taxRateSetter.show(fm, "Set Tax Rate");
        });
    }

    // this method retrieves the latest tax rate when activity opens and displays it
    @SuppressLint("SetTextI18n")
    private void initTaxRate(){
        tvTaxRate = findViewById(R.id.textTaxRate);
        SharedPreferences getTaxRate = getSharedPreferences("MyTaxRatePreferences",
                Context.MODE_PRIVATE);

        // retrieve current tax rate from shared preferences
        Map<String, ?> allEntries = getTaxRate.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            TAX_RATE = Double.parseDouble(String.valueOf(entry.getValue()));

            // divides the tax rate by 100.0 if it is greater than 1
            if (TAX_RATE > 1)
                TAX_RATE /= 100.0;

            tvTaxRate.setText("" + TAX_RATE); // displays the last set tax rate
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    // gets new tax rate from the tax rate setter activity and displays it
    public void didFinishSaving(double taxRate) {
        tvTaxRate = findViewById(R.id.textTaxRate);
        TAX_RATE = taxRate;
        tvTaxRate.setText(""+ TAX_RATE); // displays the new tax rate
    }
}