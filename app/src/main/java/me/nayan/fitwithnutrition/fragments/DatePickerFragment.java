package me.nayan.fitwithnutrition.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

import me.nayan.fitwithnutrition.BreakfastChart;
import me.nayan.fitwithnutrition.DinnerChart;
import me.nayan.fitwithnutrition.LunchChart;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int year, month, day;
    Activity activity;

    @SuppressLint("ValidFragment")
    public DatePickerFragment(Activity activity) {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

        Log.d("Tag", activity.getLocalClassName());

        if (activity.getLocalClassName().equals("BreakfastChart")){
            ((BreakfastChart)getActivity()).onDateSet(year, month, day);
        } else if(activity.getLocalClassName().equals("LunchChart")){
            ((LunchChart)getActivity()).onDateSet(year, month, day);
        } else if(activity.getLocalClassName().equals("LunchChart")){
            ((DinnerChart)getActivity()).onDateSet(year, month, day);
        }




    }

}
