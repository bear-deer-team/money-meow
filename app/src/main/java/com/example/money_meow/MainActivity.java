package com.example.money_meow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.money_meow.database.MongoDBConnection;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity {
    private static String Appid = "mongodb-demo-ynesw";
    private static App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to MongoDB Cluster
        MongoDBConnection.connect(this);
        app = new App(new AppConfiguration.Builder(Appid).build());
        app.loginAsync(Credentials.anonymous(), new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if(result.isSuccess())
                {
                    Log.v("User","Logged In Successfully");

                }
                else
                {
                    Log.v("User","Failed to Login");
                }
            }
        });
    }
}