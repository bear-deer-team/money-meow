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
import com.example.money_meow.setting.Settings;

public class Information extends BaseActivity {
    private Button addTransBtn, homeBtn, historyBtn, searchBtn, settingBtn;
    private Button returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        homeBtn = (Button) findViewById(R.id.HomeBtn);
        historyBtn = (Button) findViewById(R.id.HistoryBtn);
        addTransBtn = (Button) findViewById(R.id.AddTransBtn);
        searchBtn = (Button) findViewById(R.id.SearchBtn);
        settingBtn = (Button) findViewById(R.id.SettingBtn);
        returnBtn = (Button) findViewById(R.id.returnBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Information.this, Home.class);
                startActivity(intent);
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Information.this, Settings.class);
                startActivity(intent);
            }
        });
    }

}
