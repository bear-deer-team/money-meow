package com.example.money_meow.database;

import org.bson.Document;


import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class MongoDBQuery {
    public static boolean isExist(String database, String collection, Document document) {
        Document result = queryOne(database, collection, document);

        if (result == null) {
            return false;
        } else {
            return true;
        }
    }
    public static Document queryOne(String database, String collection, Document document) {
        MongoCollection<Document> mongoCollection = MongoDBConnection.accessDatabase(database, collection);

        return mongoCollection.findOne(document).get();
    }

}
