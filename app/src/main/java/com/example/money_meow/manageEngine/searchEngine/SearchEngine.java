package com.example.money_meow.manageEngine.searchEngine;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.R;
import com.example.money_meow.home.Home;
import com.example.money_meow.manageEngine.filter.Filter;
import com.example.money_meow.manageEngine.statistic.StatisticsAction;
import com.example.money_meow.setting.Settings;
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

    private Filter filter = new Filter();
    private Button homeBtn, historyBtn, settingBtn,addTransBtn, filterBtn;
    public static ImageView searchImg;

    private List<Transaction> crList = new ArrayList<>(TransactionList.mainList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching);

        rcvTransList = findViewById(R.id.TransactionList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcvTransList.setLayoutManager(gridLayoutManager);

        transactionAdapter = new TransactionAdapter(crList,this);
        rcvTransList.setAdapter(transactionAdapter);

        homeBtn = findViewById(R.id.HomeBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        addTransBtn = findViewById(R.id.AddTransBtn);
        settingBtn = findViewById(R.id.SettingBtn);
        filterBtn = findViewById(R.id.FilterBtn);
        filterBtn.setTransformationMethod(null);

        searchText = findViewById(R.id.edit_text_search);
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

        assert submit != null;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(endDayText != null && startDayText != null && !endDayText.getText().toString().isEmpty() && !startDayText.getText().toString().isEmpty()) {
                    filter.setChecktoFilter(true);
                    getListByFilter(filter);
                    startDayText.setText(null);
                    endDayText.setText(null);
                    bottomSheetDialog.dismiss();
                }
            }
        });

        endDayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                Filter filterDay = new Filter(endDayText);
                int customStyle = R.style.CustomDatePickerDialog;
                DatePickerDialog dialog = new DatePickerDialog(
                        SearchEngine.this,
                        customStyle,
                        filterDay.mDateSetListener,
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
                Filter filterDay = new Filter(startDayText);
                int customStyle = R.style.CustomDatePickerDialog;
                DatePickerDialog dialog = new DatePickerDialog(
                        SearchEngine.this,
                        customStyle,
                        filterDay.mDateSetListener,
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
    private List<Transaction> getListBySearch(Filter filter, String keyword) {
        List<Transaction> resSearch = filter.getListBySearch(keyword,crList);
        return resSearch;
    }

    private void getListByFilter(Filter filter) {
        List<Transaction> resFilter = filter.getListByFilter(filter, crList, startDayText, endDayText);
        setCrList(resFilter);
        transactionAdapter.updateList(crList);
    }
    private TextWatcher searchValueWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            searchValue = s.toString();
            transactionAdapter.updateList(getListBySearch(filter,searchValue));
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void setCrList(List<Transaction> crList) {
        this.crList = crList;
    }
}
