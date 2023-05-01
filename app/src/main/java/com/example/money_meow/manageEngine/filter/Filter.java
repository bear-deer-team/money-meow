package com.example.money_meow.manageEngine.filter;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

public class Filter {
    private EditText dayText;

    private boolean checktoFilter = false;

    public boolean isChecktoFilter() {
        return checktoFilter;
    }

    public void setChecktoFilter(boolean checktoFilter) {
        this.checktoFilter = checktoFilter;
    }

    public Filter() {

    }

    public Filter(EditText dayText) {
        this.dayText = dayText;
    }

    public DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    dayText.setText(selectedDate);
                }
            };
}
