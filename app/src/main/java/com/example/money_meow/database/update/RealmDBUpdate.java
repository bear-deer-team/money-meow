package com.example.money_meow.database.update;

import android.util.Log;

import com.example.money_meow.transaction.Transaction;

import io.realm.Realm;

public class RealmDBUpdate {
    public static void updateRealm(Transaction transaction) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Transaction object = realm.where(Transaction.class).equalTo("id", transaction.getId()).findFirst();
                if (object != null) {
                    object.deleteFromRealm();
                    realm.copyToRealmOrUpdate(transaction);
                    Log.d("TAG", "Transaction updated.");
                } else {
                    Log.d("TAG", "Transaction not found.");
                }
            }
        });
        realm.close();
    }

}
