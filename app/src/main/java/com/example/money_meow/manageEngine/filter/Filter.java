package com.example.money_meow.manageEngine.filter;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.money_meow.transaction.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<Transaction> getListByFilter(Filter a, List<Transaction> crList, EditText startDayText, EditText endDayText) {
        List<Transaction> resFilter = new ArrayList<>();
        if(a.isChecktoFilter()) {
            for (Transaction transaction : crList) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date startDate = format.parse(startDayText.getText().toString());
                    Date endDate = format.parse(endDayText.getText().toString());
                    if(transaction.getTransactionTime().after(startDate) && transaction.getTransactionTime().before(endDate)) {
                        resFilter.add(transaction);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            a.setChecktoFilter(false);
        } else {
            resFilter = crList;
        }
        return resFilter;
    }

    public List<Transaction> getListBySearch(String keyword, List<Transaction> crList) {
        List<Transaction> resSearch = new ArrayList<>();
        if (keyword != null && !keyword.isEmpty()) {
            for (Transaction transaction : crList) {
                if (transaction.getTransactionCategory() != null &&
                        transaction.getTransactionCategory().getCategoryName() != null &&
                        transaction.getTransactionCategory().getCategoryName().contains(keyword)) {
                    resSearch.add(transaction);
                }
            }
        } else {
            resSearch = crList;
        }
        return resSearch;
    }
}
