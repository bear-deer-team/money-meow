package com.example.money_meow.database;


import android.content.Context;
import android.util.Log;


import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MongoDBConnection {
    private static String Appid = "money-meow-iujqj";
    private static App app;

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
