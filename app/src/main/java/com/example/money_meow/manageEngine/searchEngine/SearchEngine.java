package com.example.money_meow.manageEngine.searchEngine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

public class SearchEngine extends BaseActivity {
    private RecyclerView rcvTransList;
    private TransactionAdapter transactionAdapter;

    private  String searchValue;
    private EditText searchText;
    private Button homeBtn, historyBtn, settingBtn,addTransBtn;
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

        searchText = (EditText) findViewById(R.id.edit_text_search);
        searchText.addTextChangedListener(searchValueWatcher);
        searchImg = findViewById(R.id.imageSearch);
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
                if (transaction.getTransactionCategory() != null &&
                        transaction.getTransactionCategory().getCategoryName() != null &&
                        transaction.getTransactionCategory().getCategoryName().contains(keyword)) {
                    res.add(transaction);
                }
            }
        } else {
            res = TransactionList.mainList;
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

}
