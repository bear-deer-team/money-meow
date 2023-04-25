package com.example.money_meow.database.update;

import android.util.Log;

import com.example.money_meow.database.MongoDBConnection;

import org.bson.Document;
import org.bson.conversions.Bson;

import io.realm.mongodb.mongo.MongoCollection;

public class MongoDBUpdate {
    public static void update(String database, String collection, Document filter, Document update) {
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
