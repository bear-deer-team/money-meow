package com.example.money_meow.manageEngine.filter;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.category.Category;
import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    public static List<Transaction> getListByCurrentMonth() {
        List<Transaction> resFilter = new ArrayList<>();
        Date endDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        Date startDate = calendar.getTime();
        for (Transaction transaction : TransactionList.mainList) {
            if(transaction.getTransactionTime().after(startDate) && transaction.getTransactionTime().before(endDate)) {
                resFilter.add(transaction);
            }
        }
        return resFilter;
    }

    public static List<Transaction> getExpenseList(List<Transaction> sourceList) {
        List<Transaction> resFilter = new ArrayList<>();
        for (Transaction transaction : sourceList) {
            if(transaction.getTransactionType().equals("extense")) {
                resFilter.add(transaction);
            }
        }
        return resFilter;
    }

    public static List<Transaction> getIncomeList(List<Transaction> sourceList) {
        List<Transaction> resFilter = new ArrayList<>();
        for (Transaction transaction : sourceList) {
            if(transaction.getTransactionType().equals("income")) {
                resFilter.add(transaction);
            }
        }
        return resFilter;
    }

    public static List<Transaction> getListByCategory(List<Transaction> sourceList, String sampleCategory) {
        List<Transaction> resFilter = new ArrayList<>();
        for (Transaction transaction : sourceList) {
            if(transaction.getTransactionCategory().getCategoryName().equals(sampleCategory)) {
                resFilter.add(transaction);
            }
        }
        return resFilter;
    }


}
