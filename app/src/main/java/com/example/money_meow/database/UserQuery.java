package com.example.money_meow.database;

import com.example.money_meow.account.Account;

import org.bson.Document;

public class UserQuery {
    public static Account FindByUserName(String userName){
        Document userDoc = new Document(MongoDBQuery.queryOne("MoneyMeow","users",new Document("userName",userName)));
        Account account = new Account(userDoc.getString("name"),userDoc.getString("userName"),userDoc.getString("email"),userDoc.getString("password"), Double.parseDouble(userDoc.getString("balance")));
        return account;
    }
}
