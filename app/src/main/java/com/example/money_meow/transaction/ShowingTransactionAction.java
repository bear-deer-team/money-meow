package com.example.money_meow.transaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.R;
import com.example.money_meow.home.HistoryListForHome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowingTransactionAction extends BaseActivity {
    private RecyclerView rcvHistory;
    private HistoryListForHome historyListForHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_list);

        rcvHistory = findViewById(R.id.historylist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcvHistory.setLayoutManager(gridLayoutManager);

        historyListForHome = new HistoryListForHome(getList(),this);
        rcvHistory.setAdapter(historyListForHome);

        Spinner timeList = (Spinner) findViewById(R.id.timeList);

        List<String> items = new ArrayList<>();
        items.add("Choose your time");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.time_list_item, items);

        timeList.setAdapter(adapter);
        timeList.setSelection(0);

        timeList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }

    private List<Transaction> getList() {
        List<Transaction> res = TransactionList.mainList;
        Collections.sort(res);
        return res;
    }
}
