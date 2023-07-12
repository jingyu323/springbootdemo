package com.rain.insert;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;

public class UpdateMany {
    private final static Logger logger = LoggerFactory.getLogger(UpdateMany.class);

    public static void main(String[] args) {
        String uri = "mongodb://192.168.182.142:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            Bson query = gt("num_mflix_comments", 50);
            Bson updates = Updates.combine(
                    Updates.addToSet("genres", "Frequently Discussed"),
                    Updates.currentTimestamp("lastUpdated"));

            try {
                UpdateResult result = collection.updateMany(query, updates);
                logger.info("Modified document count: " + result.getModifiedCount());
            } catch (MongoException me) {
                logger.info("Unable to update due to an error: " + me);
            }

        }

    }
}
