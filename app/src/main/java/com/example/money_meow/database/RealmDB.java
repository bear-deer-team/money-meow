package com.example.money_meow.database;

import android.util.Log;

import com.example.money_meow.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class RealmDB {
    public static void configFile(){
        // Lấy đối tượng RealmConfiguration mặc định hiện tại
        RealmConfiguration config = Realm.getDefaultConfiguration();
        // Xóa file Realm hiện tại
        Realm.deleteRealm(config);
        // Tạo file Realm mới với tên bất kỳ
        RealmConfiguration newConfig = new RealmConfiguration.Builder()
                .build();
        Realm.setDefaultConfiguration(newConfig);
    }

    public static void addToRealm(List<? extends RealmObject> list) {
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
