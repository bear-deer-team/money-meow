package com.example.money_meow.database.delete;

import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionList;


import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public class TransactionDelete {
    public static void deleteUptoDB() {
        if (!TransactionList.deleteList.isEmpty()) {
            List< Transaction> deleteList = TransactionList.deleteList;
            RealmDBDelete.deleteFromRealm(deleteList);
            for (int i = 0; i < deleteList.size(); i++) {
                MongoDBDelete.deleteOne("MoneyMeow","transactions", new Document().append("id", deleteList.get(i).getId()));
            }
        }

    }
}
