package com.example.money_meow.manageEngine.searchEngine;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.R;
import com.example.money_meow.category.CategoryList;
import com.example.money_meow.home.HistoryListForHome;
import com.example.money_meow.home.Home;
import com.example.money_meow.information.Information;
import com.example.money_meow.manageEngine.statistic.StatisticsAction;
import com.example.money_meow.setting.Settings;
import com.example.money_meow.transaction.CategoryAdapter;
import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionAction;
import com.example.money_meow.transaction.TransactionList;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchEngine extends BaseActivity {
    private RecyclerView rcvTransList;
    private TransactionAdapter transactionAdapter;

    private  String searchValue;
    private EditText searchText, startDayText, endDayText;
    private Button homeBtn, historyBtn, settingBtn,addTransBtn, filterBtn;
    public static ImageView searchImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching);

        rcvTransList = findViewById(R.id.TransactionList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcvTransList.setLayoutManager(gridLayoutManager);

        transactionAdapter = new TransactionAdapter(getList(searchValue),this);
        rcvTransList.setAdapter(transactionAdapter);

        homeBtn = findViewById(R.id.HomeBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        addTransBtn = findViewById(R.id.AddTransBtn);
        settingBtn = findViewById(R.id.SettingBtn);
        filterBtn = findViewById(R.id.FilterBtn);
        filterBtn.setTransformationMethod(null);

        searchText = (EditText) findViewById(R.id.edit_text_search);
        searchText.addTextChangedListener(searchValueWatcher);
        searchImg = findViewById(R.id.imageSearch);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SearchEngine.this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.filter_date);
        startDayText = bottomSheetDialog.findViewById(R.id.editStartDay);
        endDayText = bottomSheetDialog.findViewById(R.id.editEndDay);
        Button submit = bottomSheetDialog.findViewById(R.id.acceptBtn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        endDayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        SearchEngine.this,
                        mEndDateSetListener,
                        year,
                        month,
                        day);
                dialog.show();
            }
        });

        startDayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        SearchEngine.this,
                        mStartDateSetListener,
                        year,
                        month,
                        day);
                dialog.show();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchEngine.this, Home.class);
                startActivity(intent);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchEngine.this, StatisticsAction.class);
                startActivity(intent);
            }
        });

        addTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchEngine.this, TransactionAction.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchEngine.this, Settings.class);
                startActivity(intent);
            }
        });
    }
    private List<Transaction> getList(String keyword) {
        List<Transaction> res = new ArrayList<>();
        if (keyword != null && !keyword.isEmpty()) {
            for (Transaction transaction : TransactionList.mainList) {
                System.out.println(endDayText + " " + startDayText);
                if(endDayText != null && startDayText != null && !endDayText.getText().toString().isEmpty() && !startDayText.getText().toString().isEmpty()) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date startDate = format.parse(startDayText.getText().toString());
                        Date endDate = format.parse(endDayText.getText().toString());
                        if (transaction.getTransactionCategory() != null &&
                                transaction.getTransactionCategory().getCategoryName() != null &&
                                transaction.getTransactionCategory().getCategoryName().contains(keyword) && transaction.getTransactionTime().after(startDate) &&
                                transaction.getTransactionTime().before(endDate)) {
                            res.add(transaction);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (transaction.getTransactionCategory() != null &&
                            transaction.getTransactionCategory().getCategoryName() != null &&
                            transaction.getTransactionCategory().getCategoryName().contains(keyword)) {
                        res.add(transaction);
                    }
                }
            }
        } else {
            System.out.println(endDayText + " " + startDayText);
            if (endDayText != null && startDayText != null && !endDayText.getText().toString().isEmpty() && !startDayText.getText().toString().isEmpty()) {
                for (Transaction transaction : TransactionList.mainList) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date startDate = format.parse(startDayText.getText().toString());
                        Date endDate = format.parse(endDayText.getText().toString());
                        if (transaction.getTransactionTime().after(startDate) && transaction.getTransactionTime().before(endDate)) {
                            res.add(transaction);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                res = TransactionList.mainList;
            }
        }
        return res;
    }

    private TextWatcher searchValueWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            searchValue = s.toString();
            transactionAdapter.updateList(getList(searchValue));
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private DatePickerDialog.OnDateSetListener mEndDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    endDayText.setText(selectedDate);
                }
            };
    private DatePickerDialog.OnDateSetListener mStartDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    startDayText.setText(selectedDate);
                }
            };
}
