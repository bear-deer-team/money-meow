package com.example.money_meow.database.update;

import com.example.money_meow.database.insert.RealmInsert;
import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionList;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TransactionUpdate {
    public static void updateUptoDB() {
        if (!TransactionList.updateList.isEmpty()) {
            List<Transaction> updateList = TransactionList.updateList;
            for (int i = 0; i < updateList.size(); i++) {
                Document document = updateList.get(i).toDocument();
                Document query = new Document("id", updateList.get(i).getId());
                MongoDBUpdate.update("MoneyMeow", "transactions", query, document);
                RealmDBUpdate.updateRealm(updateList.get(i));
            }
        }
    }
}
