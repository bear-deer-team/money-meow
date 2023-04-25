package com.example.money_meow.database.delete;

import android.util.Log;

import com.example.money_meow.database.MongoDBConnection;

import org.bson.Document;
import org.bson.conversions.Bson;

import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class MongoDBDelete {
    public static void deleteOne(String database, String collection, Document filter) {
        MongoCollection<Document> mongoCollection = MongoDBConnection.accessDatabase(database, collection);

        mongoCollection.deleteOne(filter).getAsync(task -> {
            if (task.isSuccess()) {
                Log.v("Data", "Delete data successfully!");
            } else {
                Log.v("Data", "Error:"+task.getError().toString());
            }
        });

    }
    public static void deleteMany(String database, String collection, Bson filter) {
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
