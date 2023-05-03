package com.example.money_meow.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.MainActivity;
import com.example.money_meow.R;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.account.login.LoginAction;
import com.example.money_meow.database.delete.TransactionDelete;
import com.example.money_meow.database.insert.TransactionInsert;
import com.example.money_meow.home.Home;
import com.example.money_meow.information.Information;
import com.example.money_meow.manageEngine.statistic.StatisticsAction;
import com.example.money_meow.transaction.TransactionAction;
import com.example.money_meow.transaction.TransactionList;

public class Settings extends BaseActivity {
    Button infor, editAcc, logOut, display;
    Button addTransBtn, homeBtn, historyBtn, transactionBtn, settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        infor = (Button) findViewById(R.id.infor);
        editAcc = (Button) findViewById(R.id.editAcc);
        logOut = (Button) findViewById(R.id.logout);
        display = (Button) findViewById(R.id.display);

        addTransBtn = findViewById(R.id.AddTransBtn);
        homeBtn = findViewById(R.id.HomeBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        transactionBtn = findViewById(R.id.transactionBtn);
        settingBtn = findViewById(R.id.SettingBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, Home.class);
                startActivity(intent);
            }
        });
        addTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, TransactionAction.class);
                startActivity(intent);
            }
        });

        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, Information.class);
                startActivity(intent);
            }
        });

        editAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, AccountSetting.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransactionDelete.deleteUptoDB();
                TransactionInsert.insertUptoDB();
                TransactionList.destroy();
                LoginAccount.logout();
                SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, StatisticsAction.class);
                startActivity(intent);
            }
        });


    }
}
