package com.alistair.mycontactlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class RelationshipPicker extends DialogFragment {
    String relationship;

    public RelationshipPicker(){}

    // this interface sends the relationship value to the class that implements it
    public interface SaveRadioListener{
        void didFinishSetting(String s);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        final View view = inflater.inflate(R.layout.select_relationship, container);

        RadioButton rbFriend = view.findViewById(R.id.radioFriend);
        RadioButton rbFamily = view.findViewById(R.id.radioFamily);
        RadioButton rbCoworker = view.findViewById(R.id.radioCoworker);
        RadioButton rbAcquaintance = view.findViewById(R.id.radioAcquaintance);
        Button okayButton = view.findViewById(R.id.buttonOkay);
        Button cancelButton = view.findViewById(R.id.btnCancel);

        // when the save button is clicked it checks if there is any radio button that is checked
        // then passes that value to be returned back to the main activity
        okayButton.setOnClickListener(v -> {
            if (rbFriend.isChecked())
                relationship = "Friend";
            else if (rbFamily.isChecked())
                relationship = "Family";
            else if (rbCoworker.isChecked())
                relationship = "Coworker";
            else if (rbAcquaintance.isChecked())
                relationship = "Acquaintance";

            // passes the new set relationship to saveRelationship method
            saveRelationship(relationship);
        });

        // when the cancel button is clicked, it passes "no set" value
        // to be returned back to the main activity
        cancelButton.setOnClickListener(v -> {
            saveRelationship("No Set!");
        });

        return view;
    }

    // this method receives a new relationship value and passes it it back to the interface
    private void saveRelationship(String ratings){
        SaveRadioListener activity = (SaveRadioListener) getActivity();
        assert activity != null;

        // passes a value to be used in the class the implements this interface
        activity.didFinishSetting(ratings);

        // close the dialog
        Objects.requireNonNull(getDialog()).dismiss();
    }
}
