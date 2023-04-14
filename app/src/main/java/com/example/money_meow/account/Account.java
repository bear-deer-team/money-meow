package com.example.money_meow.account;

import android.util.Log;

import com.example.money_meow.balance.Balance;
import com.example.money_meow.database.AddUserToDB;
import com.example.money_meow.database.MongoDBInsert;

import org.bson.Document;

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
    public void addNewUserToDB() {
        MongoDBInsert.insertOne("MoneyMeow",
                        "users",
                        new Document()
                        .append("name", this.name)
                        .append("username", this.userName)
                        .append("email", this.email)
                        .append("password", this.password)
                        .append("balance", this.balance.getAmount()));
    }
}
