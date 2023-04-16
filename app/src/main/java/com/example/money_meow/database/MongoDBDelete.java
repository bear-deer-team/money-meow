package com.example.money_meow.database;

import android.util.Log;

import org.bson.Document;
import org.bson.conversions.Bson;

import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class MongoDBDelete {
    public static void delete(String database, String collection, Bson filter) {
        MongoCollection<Document> mongoCollection = MongoDBConnection.accessDatabase(database, collection);

        mongoCollection.deleteMany(filter).getAsync(task -> {
            if (task.isSuccess()) {
                Log.v("Data", "Delete data successfully!");
            } else {
                Log.v("Data", "Error:"+task.getError().toString());
            }
        });

    }
}
