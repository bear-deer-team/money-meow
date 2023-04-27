package com.example.money_meow.database.query;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmQuery {
    public static <T extends RealmObject> List<T> getDB(Class<T> clazz) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<T> list = realm.where(clazz).findAll();
        List<T> result = realm.copyFromRealm(list);
        realm.close();
        if (list != null ) {
            Log.d("getDB",  "success get" + clazz.getSimpleName());
        }
        return result;
    }
}
