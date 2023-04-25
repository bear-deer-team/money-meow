package com.example.money_meow.information;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.R;
import com.example.money_meow.home.Home;
import com.example.money_meow.manageEngine.statistic.StatisticsAction;
import com.example.money_meow.setting.Settings;
import com.example.money_meow.transaction.TransactionAction;

public class Information extends BaseActivity {
    private Button  homeBtn, historyBtn, searchBtn, settingBtn,addTransBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        homeBtn = (Button) findViewById(R.id.HomeBtn);
        addTransBtn = findViewById(R.id.AddTransBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        //searchBtn = findViewById(R.id.SearchBtn);
        settingBtn = findViewById(R.id.SettingBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Information.this, Home.class);
                startActivity(intent);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Information.this, StatisticsAction.class);
                startActivity(intent);
            }
        });

        addTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Information.this, TransactionAction.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Information.this, Settings.class);
                startActivity(intent);
            }
        });

    }

}
