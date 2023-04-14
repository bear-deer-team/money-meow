package com.example.money_meow.database;


import android.content.Context;
import android.util.Log;


import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MongoDBConnection {
    private static final String Appid = "money-meow-jufwk";
    private static App app;
    private static User user;

    public static App getApp() {
        return app;
    }

    public static void connect(Context context) {
        Realm.init(context);
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
