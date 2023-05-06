package com.ptit.healthcare.utils;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;


public class TimePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = 06;
        int minute = 00;

        return new CustomTimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(),
                hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}