package com.rain.insert;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public class ReplaceMany {

    public static void main(String[] args) {
        String uri = "mongodb://192.168.182.142:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            Bson query = eq("title", "Music of the Heart");

            Document replaceDocument = new Document().
                    append("title", "50 Violins").
                    append("fullplot", " A dramatization of the true story of Roberta Guaspari who co-founded the Opus 118 Harlem School of Music");
            ReplaceOptions opts = new ReplaceOptions().upsert(true);
            UpdateResult result = collection.replaceOne(query, replaceDocument, opts);
            System.out.println("Modified document count: " + result.getModifiedCount());
            System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed


        } catch (MongoException me) {
            System.err.println("Unable to replace due to an error: " + me);
        }
    }


}
