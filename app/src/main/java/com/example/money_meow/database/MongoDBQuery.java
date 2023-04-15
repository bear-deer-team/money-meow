package com.example.money_meow.database;

import android.util.Log;

import org.bson.Document;


import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class MongoDBQuery {
    private static boolean isExisted = false;
    public static boolean isExist(String database, String collection, Document document) {

        User user = MongoDBConnection.getApp().currentUser();
        MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);

        AtomicBoolean result = new AtomicBoolean(false);
        mongoCollection.findOne(document).getAsync(task -> {
            if (task.isSuccess()) {
                Document rs = task.get();
                Log.v("EXAMPLE", "successfully found a document" + rs);
                isExisted = true;
            } else {
                Log.e("EXAMPLE", "failed to find document with: ", task.getError());
                isExisted = false;
            }
        });
        return isExisted;
    }
    public static void queryOne(String database, String collection, Document document) {
        User user = MongoDBConnection.getApp().currentUser();
        MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);

        mongoCollection.findOne(document).getAsync(task -> {
            if (task.isSuccess()) {
                Document result = task.get();
                Log.v("EXAMPLE", "successfully found a document: " + result);
            } else {
                Log.e("EXAMPLE", "failed to find document with: ", task.getError());
            }
        });
    }


}
