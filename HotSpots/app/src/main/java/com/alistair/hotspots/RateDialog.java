package com.alistair.hotspots;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class RateDialog extends DialogFragment {
    RatingBar rbBeer;
    RatingBar rbWine;
    RatingBar rbMusic;
    Button buttonSave;

    /* this interface passes the values back to main activity when save button is clicked */
    public interface SaveRatingBarListener{
        void didFinishRating(float beer, float wine, float music);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        final View view = inflater.inflate(R.layout.rate, container);

        // instantiates layout widget objects
        rbBeer = view.findViewById(R.id.rbBeer);
        rbWine = view.findViewById(R.id.rbWine);
        rbMusic = view.findViewById(R.id.rbMusic);
        buttonSave = view.findViewById(R.id.buttonSave);

        // listens to the click events on the save button and gets the ratings
        buttonSave.setOnClickListener(v -> {
            float beerRating = rbBeer.getRating();
            float wineRating = rbWine.getRating();
            float musicRating = rbMusic.getRating();

            // pass the values to the saveRatings method
            saveRatings(beerRating,wineRating,musicRating);
        });

        return view;
    }

    private void saveRatings(float beerRating, float wineRating, float musicRating){
        SaveRatingBarListener activity = (SaveRatingBarListener) getActivity();
        assert activity != null;

        // passing the ratings to be used when this method is implemented in the main activity
        activity.didFinishRating(beerRating,wineRating,musicRating);

        // closes the dialog
        Objects.requireNonNull(getDialog()).dismiss();
    }
}
