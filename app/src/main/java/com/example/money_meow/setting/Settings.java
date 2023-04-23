package com.example.money_meow.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.MainActivity;
import com.example.money_meow.R;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.account.login.LoginAction;
import com.example.money_meow.home.Home;
import com.example.money_meow.information.Information;
import com.example.money_meow.transaction.TransactionAction;

public class Settings extends AppCompatActivity {
    Button infor, editAcc, logOut, display;
    Button addTransBtn, homeBtn, historyBtn, searchBtn, settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        infor = (Button) findViewById(R.id.account);
        editAcc = (Button) findViewById(R.id.editAcc);
        logOut = (Button) findViewById(R.id.logout);
        display = (Button) findViewById(R.id.display);

        addTransBtn = findViewById(R.id.AddTransBtn);
        homeBtn = findViewById(R.id.HomeBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        searchBtn = findViewById(R.id.SearchBtn);
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
                LoginAccount.logout();
                SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}
