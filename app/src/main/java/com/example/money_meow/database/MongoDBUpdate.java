package com.example.money_meow.database;

import android.util.Log;

import org.bson.Document;
import org.bson.conversions.Bson;

import io.realm.mongodb.mongo.MongoCollection;

public class MongoDBUpdate {
    public static void update(String database, String collection, Bson filter, Bson update) {
        MongoCollection<Document> mongoCollection = MongoDBConnection.accessDatabase(database, collection);

        mongoCollection.updateMany(filter, update).getAsync(task -> {
            if (task.isSuccess()) {
                Log.v("Data", "Update data successfully!");
            } else {
                Log.v("Data", "Error:"+task.getError().toString());
            }
        });

    }
}
