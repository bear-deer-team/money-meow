package com.example.money_meow.database.insert;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.exceptions.RealmException;

public class RealmInsert {
    public static void insertMany(List<? extends RealmObject> list) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(list);
                }
            });
        } catch (RealmException e) {
            Log.e("addToRealm", "Error: " + e.getMessage());
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }
}
