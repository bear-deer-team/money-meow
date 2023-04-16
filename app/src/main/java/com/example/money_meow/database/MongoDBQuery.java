package com.example.money_meow.database;

import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

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
    public static List<Document> find(String database, String collection, Document document) {
        MongoCollection<Document> mongoCollection = MongoDBConnection.accessDatabase(database, collection);
        List<Document> results = new ArrayList<>();
        RealmResultTask<MongoCursor<Document>> resultTask = mongoCollection.find(document).iterator();
        MongoCursor<Document> cursor = resultTask.get();
        while (cursor.hasNext()) {
            results.add(cursor.next());}
        cursor.close();
        return results;
    }

}
