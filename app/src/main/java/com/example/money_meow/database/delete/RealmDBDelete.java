package com.example.money_meow.database.delete;

import android.util.Log;

import com.example.money_meow.transaction.Transaction;

import java.util.List;

import io.realm.Realm;

public class RealmDBDelete {
    public static void deleteFromRealm(List<Transaction> transactions) {
        Realm realm = Realm.getDefaultInstance();
        for(int i=0;i<transactions.size();i++) {
            Transaction transaction = realm.where(Transaction.class)
                    .equalTo("id", transactions.get(i).getId())
                    .findFirst();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    transaction.deleteFromRealm();
                }
            });
            Log.v("delete","deleted" + transactions.get(i).getName());
        }

        realm.close();
    }
}
