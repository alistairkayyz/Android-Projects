package com.alistair.mycontactlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Objects;

public class DatePickerDialog extends DialogFragment {
    Calendar selectedDate;

    // this interface sends the new date to the class that implements it
    public interface SaveDateListener{
        void didFinishDatePickerDialog(Calendar selectedTime);
    }

    public DatePickerDialog(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        final View view = inflater.inflate(R.layout.select_date, container);

        Objects.requireNonNull(getDialog()).setTitle("Select Date");
        selectedDate = Calendar.getInstance(); // instantiates the date object

        // instantiate the calender view object
        final CalendarView cv = view.findViewById(R.id.calendarView);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectedDate.set(year,month,dayOfMonth); // sets a new selected date
            }
        });

        Button saveButton = view.findViewById(R.id.buttonSelect);
        saveButton.setOnClickListener(v->{
            saveItem(selectedDate); // pass the selected date to the save item method
        });

        Button cancelButton = view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(v ->{
            getDialog().dismiss(); // close the dialog
        });

        return view;
    }

    private void saveItem(Calendar selectedDate){
        SaveDateListener activity = (SaveDateListener) getActivity();
        assert activity != null;

        // pass the selected dated to the class that implements this method
        activity.didFinishDatePickerDialog(selectedDate);

        // close the dialog
        Objects.requireNonNull(getDialog()).dismiss();
    }
}
