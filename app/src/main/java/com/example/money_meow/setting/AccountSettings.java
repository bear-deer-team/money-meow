package com.example.money_meow.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.R;

public class AccountSettings extends AppCompatActivity {
    Button account;
    Button editAcc;
    Button logOut;
    Button display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        account = (Button) findViewById(R.id.account);
        editAcc = (Button) findViewById(R.id.editAcc);
        logOut = (Button) findViewById(R.id.logout);
        display = (Button) findViewById(R.id.display);

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAcc.setVisibility(View.VISIBLE);
                logOut.setVisibility(View.VISIBLE);
            }
        });


    }
}
