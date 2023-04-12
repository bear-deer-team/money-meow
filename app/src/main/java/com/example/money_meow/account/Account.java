package com.example.money_meow.account;

import android.util.Log;
import android.widget.Toast;

import com.example.money_meow.account.signup.SignupAction;
import com.example.money_meow.balance.Balance;
import com.example.money_meow.database.AddUserToDB;
import com.example.money_meow.database.MongoDBConnection;

import org.bson.Document;

import io.realm.mongodb.App;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;


public class Account implements AddUserToDB {
    private String name;
    private String userName;
    private String email;
    private String password;
    private Balance balance;

    public Account() {

    }

    public Account(String name, String userName, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.balance = new Balance();
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    @Override
    public void addNewUserToDB(App app) {
        User user = app.currentUser();
        MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("money-meow-db");
        MongoCollection mongoCollection = mongoDatabase.getCollection("account");

        mongoCollection.insertOne(new Document("id", user.getId())
                        .append("name", this.name)
                        .append("username", this.userName)
                        .append("email", this.email)
                        .append("password", this.password)
                        .append("balance", this.balance.getAmount()))
                        .getAsync(result -> {
            if(result.isSuccess())
            {
                Log.v("Data","Data Inserted Successfully");
            }
            else
            {
                Log.v("Data","Error:"+result.getError().toString());
            }
        });
        //Toast.makeText(getApplicationContext(),"Login Succesful",Toast.LENGTH_LONG).show();

    }
}
