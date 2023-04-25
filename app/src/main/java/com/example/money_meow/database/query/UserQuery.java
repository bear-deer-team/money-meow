package com.example.money_meow.database.query;

import com.example.money_meow.account.Account;
import com.example.money_meow.database.query.MongoDBQuery;

import org.bson.Document;

public class UserQuery {
    public static Account FindByUserName(String userName){
        Document userDoc = new Document(MongoDBQuery.queryOne("MoneyMeow","users",new Document("userName",userName)));
        Account account = new Account(userDoc.getString("name"),userDoc.getString("userName"),userDoc.getString("email"),userDoc.getString("password"), userDoc.getDouble("balance"));
        return account;
    }
}
