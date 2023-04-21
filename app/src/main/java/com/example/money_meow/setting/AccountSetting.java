package com.example.money_meow.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.R;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.home.Home;
import com.example.money_meow.information.Information;
import com.example.money_meow.transaction.TransactionAction;

public class AccountSetting extends AppCompatActivity {
    Button addTransBtn, homeBtn, historyBtn, searchBtn, settingBtn;

    TextView username, name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_edit);

        addTransBtn = findViewById(R.id.AddTransBtn);
        homeBtn = findViewById(R.id.HomeBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        searchBtn = findViewById(R.id.SearchBtn);
        settingBtn = findViewById(R.id.SettingBtn);

        username = (TextView) findViewById(R.id.acc_username);
        name = findViewById(R.id.acc_name);
        email = findViewById(R.id.acc_email);
        password = findViewById(R.id.acc_pwd);

        setAccInfor();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSetting.this, Home.class);
                startActivity(intent);
            }
        });
        addTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSetting.this, TransactionAction.class);
                startActivity(intent);
            }
        });
    }

    private void setAccInfor() {
        username.setText(LoginAccount.account.getUserName());
        name.setText(LoginAccount.account.getName());
        email.setText(LoginAccount.account.getEmail());
        String pwd = "********";
        password.setText(pwd);
    }
}
