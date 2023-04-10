package com.example.money_meow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.money_meow.database.MongoDBConnection;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to MongoDB Cluster
         MongoDBConnection.connect(this);
    }
}