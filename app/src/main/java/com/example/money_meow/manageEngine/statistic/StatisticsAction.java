package com.example.money_meow.manageEngine.statistic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.R;
import com.example.money_meow.home.Home;
import com.example.money_meow.setting.Settings;
import com.example.money_meow.transaction.TransactionAction;

public class StatisticsAction extends BaseActivity {
    private Button addTransBtn, homeBtn, historyBtn, transactionBtn, settingBtn;
    private Button byTimeBtn, byCategoryBtn, byBothBtn;

    ViewFlipper viewFlipper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
        homeBtn = (Button) findViewById(R.id.HomeBtn);
        historyBtn = (Button) findViewById(R.id.HistoryBtn);
        addTransBtn = (Button) findViewById(R.id.AddTransBtn);
        transactionBtn = (Button) findViewById(R.id.transactionBtn);
        settingBtn = (Button) findViewById(R.id.SettingBtn);

        byTimeBtn = (Button) findViewById(R.id.ByTimeBtn);
        byCategoryBtn = (Button) findViewById(R.id.ByCategoryBtn);
        byBothBtn = (Button) findViewById(R.id.ByBothBtn);

        viewFlipper1 = findViewById(R.id.view_flipper1);
        viewFlipper1.setDisplayedChild(0);

        byCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper1.setDisplayedChild(0);
            }
        });

        byTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper1.setDisplayedChild(1);
            }
        });

        byBothBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper1.setDisplayedChild(2);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsAction.this, Home.class);
                startActivity(intent);
            }
        });

        addTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsAction.this, TransactionAction.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsAction.this, Settings.class);
                startActivity(intent);
            }
        });
    }
}
