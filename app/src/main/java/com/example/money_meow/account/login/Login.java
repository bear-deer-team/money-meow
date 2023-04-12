package com.example.money_meow.account.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.R;

public class Login extends AppCompatActivity {
    //ten tai khoan
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to MongoDB Cluster
        //MongoDBConnection.connect(this);
    }
}
