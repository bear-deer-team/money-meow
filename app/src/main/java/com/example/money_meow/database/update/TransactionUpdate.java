package com.example.money_meow.database.update;

import com.example.money_meow.database.insert.RealmInsert;
import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionList;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TransactionUpdate {
    public static void insertUptoDB() {
        if (!TransactionList.updateList.isEmpty()) {
            List<Transaction> updateList = TransactionList.updateList;
            for (int i = 0; i < updateList.size(); i++) {
                RealmDBUpdate.updateRealm(updateList.get(i));
            }
        }
    }
}
