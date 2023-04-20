package com.example.money_meow.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.R;
import com.example.money_meow.home.Home;
import com.example.money_meow.information.Information;
import com.example.money_meow.transaction.TransactionAction;

public class AccountSettings extends AppCompatActivity {
    Button account, editAcc, logOut, display, addTransBtn, infoBtn, historyBtn, searchBtn, settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        account = (Button) findViewById(R.id.account);
        editAcc = (Button) findViewById(R.id.editAcc);
        logOut = (Button) findViewById(R.id.logout);
        display = (Button) findViewById(R.id.display);

        addTransBtn = findViewById(R.id.AddTransBtn);
        infoBtn = findViewById(R.id.InfoBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        searchBtn = findViewById(R.id.SearchBtn);
        settingBtn = findViewById(R.id.SettingBtn);

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSettings.this, Information.class);
                startActivity(intent);
            }
        });
        addTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSettings.this, TransactionAction.class);
                startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAcc.setVisibility(View.VISIBLE);
                logOut.setVisibility(View.VISIBLE);
            }
        });




    }
}
