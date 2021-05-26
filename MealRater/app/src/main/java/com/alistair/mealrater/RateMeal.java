package com.alistair.mealrater;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class RateMeal extends DialogFragment {
    RatingBar rateMeal;
    Button buttonSave;
    TextView tvRatings;

    public interface SaveRatingBarListener{
        void didFinishRating(String rb);
    }
    public RateMeal(){}

    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        final View view = inflater.inflate(R.layout.rate_meal, container);

        rateMeal = view.findViewById(R.id.ratingBar);
        buttonSave = view.findViewById(R.id.buttonSave);
        tvRatings = view.findViewById(R.id.textRating);

        buttonSave.setOnClickListener(v -> {
            int getNumStars = rateMeal.getNumStars();
            float getRating = rateMeal.getRating();
            saveRatings("Rating: "+ getRating + " / "+ getNumStars);
        });

        return view;
    }
    private void saveRatings(String ratings){
        SaveRatingBarListener activity = (SaveRatingBarListener) getActivity();
        assert activity != null;
        activity.didFinishRating(ratings);
        Objects.requireNonNull(getDialog()).dismiss();
    }
}
