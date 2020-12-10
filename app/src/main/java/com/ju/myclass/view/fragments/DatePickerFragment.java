package com.ju.myclass.view.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.ju.myclass.NewStudentActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private NewStudentActivity newStudentActivity;
    private EditText mEditStudentViewBirthDate;

    public DatePickerFragment(NewStudentActivity newStudentActivity, EditText mEditStudentViewBirthDate) {
        this.newStudentActivity = newStudentActivity;
        this.mEditStudentViewBirthDate = mEditStudentViewBirthDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        final Calendar c = Calendar.getInstance();
        c.set(year, month, day, 0, 0, 0);
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
        mEditStudentViewBirthDate.setText(sdf.format(c.getTime()));
        newStudentActivity.setBirthDate(c.getTime());
    }
}
