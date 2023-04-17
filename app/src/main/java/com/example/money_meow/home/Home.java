package com.example.money_meow.home;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.R;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.database.TransactionQuery;
import com.example.money_meow.information.Information;

public class Home extends AppCompatActivity {
    private RecyclerView rcvHistory;
    private Button addTransBtn,infoBtn,historyBtn,searchBtn,settingBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        rcvHistory = findViewById(R.id.historylist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcvHistory.setLayoutManager(gridLayoutManager);
        HistoryList historyList = new HistoryList(TransactionQuery.FindByUserName(LoginAccount.account.getUserName()),this);
        rcvHistory.setAdapter(historyList);
        addTransBtn = findViewById(R.id.AddTransBtn);
        infoBtn = findViewById(R.id.InfoBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        searchBtn = findViewById(R.id.SearchBtn);
        settingBtn = findViewById(R.id.SettingBtn);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Information.class);
                startActivity(intent);
            }
        });

    }

}
