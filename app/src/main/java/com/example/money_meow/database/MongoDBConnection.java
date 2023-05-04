package com.example.money_meow.database;


import android.util.Log;


import org.bson.Document;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class MongoDBConnection {
    private static final String Appid = "money-meow-jufwk";
    private static App app;

    public static App getApp() {
        return app;
    }

    public static void connect() {

        app = new App(new AppConfiguration.Builder(Appid).build());
        app.loginAsync(Credentials.emailPassword("haibaraaicute@gmail.com", "123456"), new App.Callback<User>() {
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

    public static MongoCollection<Document> accessDatabase(String database, String collection) {
        if (!MongoDBConnection.getApp().currentUser().isLoggedIn()) {
            Log.v("User", "User is automaticly logout");
            MongoDBConnection.connect();
        }

        User user = MongoDBConnection.getApp().currentUser();
        MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        return mongoCollection;
    }

}
