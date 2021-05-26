package com.alistair.contractorcalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TaxRateSetter extends DialogFragment {

    public TaxRateSetter(){}

    public interface SaveTaxRateListener{
        void didFinishSaving(double taxRate);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.set_tax_rate, container);

        EditText getTaxRate = view.findViewById(R.id.editTaxRate);
        Button buttonSave = view.findViewById(R.id.buttonSaveTaxRate);

        buttonSave.setOnClickListener(v -> {
            if(Double.parseDouble(String.valueOf(getTaxRate.getText())) > 0.0){
                String taxRate = String.valueOf(getTaxRate.getText());

                this.getActivity().getSharedPreferences("MyTaxRatePreferences",
                        Context.MODE_PRIVATE).edit().putString("taxrate", taxRate).apply();

                saveItem(taxRate);
            }
            else {
                Toast.makeText(this.getActivity().getApplicationContext(),
                        "Tax Rate must be > 0.0 &  <= 1", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void saveItem(String item){
        SaveTaxRateListener activity = (SaveTaxRateListener) getActivity();
        double taxRate = Double.parseDouble(item);
        if (taxRate > 1)
            taxRate /= 100.0;
        activity.didFinishSaving(taxRate);
        getDialog().dismiss();
    }
}
