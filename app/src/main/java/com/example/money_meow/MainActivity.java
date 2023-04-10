package com.example.money_meow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.money_meow.database.MongoDBConnection;
import com.example.money_meow.user.login.Login;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Button start_btn = findViewById(R.id.start_button);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT).show();
            }
        });
        // Connect to MongoDB Cluster
         //MongoDBConnection.connect(this);
    }
}