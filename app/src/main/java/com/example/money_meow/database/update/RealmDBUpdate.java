package com.example.money_meow.database.update;

import android.util.Log;

import com.example.money_meow.transaction.Transaction;

import io.realm.Realm;

public class RealmDBUpdate {
    public static void updateRealm(Transaction trans){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Transaction object = realm.where(Transaction.class).equalTo("id",trans.getId()).findFirst();
                if (object != null) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(object);
                        }
                    });
                    Log.d("TAG", "Transaction updated.");
                } else {
                    Log.d("TAG", "Transaction not found.");
                }
            }
        });
        realm.close();

    }
}
