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



}
