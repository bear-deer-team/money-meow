package com.example.money_meow.home;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.R;
import com.example.money_meow.category.Category;
import com.example.money_meow.database.CategoryQuery;
import com.example.money_meow.database.TransactionQuery;
import com.example.money_meow.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private RecyclerView rcvHistory;
    private Button addTransBtn,infoBtn,historyBtn,searchBtn,settingBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        rcvHistory = findViewById(R.id.historylist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcvHistory.setLayoutManager(gridLayoutManager);
        HistoryList historyList = new HistoryList(getHistoryList(),this);
        rcvHistory.setAdapter(historyList);
        addTransBtn = findViewById(R.id.AddTransBtn);
        infoBtn = findViewById(R.id.InfoBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        searchBtn = findViewById(R.id.SearchBtn);
        settingBtn = findViewById(R.id.SettingBtn);


    }

    private List<Transaction> getHistoryList() {
        //List<Transaction> list1 = new ArrayList<>(TransactionQuery.FindByUserName("test"));
        List<Transaction> list2 = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            list2.add(list1.get(i));
//        }
        list2.add(new Transaction("test", CategoryQuery.FindByName("food"),12,""));
        return list2;
    }
}
