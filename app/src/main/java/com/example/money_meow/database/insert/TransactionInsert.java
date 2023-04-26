package com.example.money_meow.database.insert;

import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionList;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TransactionInsert {
    public static void insertUptoDB() {
        if (!TransactionList.addList.isEmpty()) {
            List<Transaction> insertList = TransactionList.addList;
            RealmInsert.insertMany(insertList);
            List<Document> documents = new ArrayList<>();
            for (int i = 0; i < insertList.size(); i++) {
                documents.add(insertList.get(i).toDocument());
            }
            MongoDBInsert.insertMany("MoneyMeow","transactions", documents);
        }
    }
}
