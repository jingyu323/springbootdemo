package com.rain.insert;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class InsertMany {
    private final static Logger logger = LoggerFactory.getLogger(InsertMany.class);

    public static void main(String[] args) {
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb://192.168.182.142:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            List<Document> movieList = Arrays.asList(
                    new Document().append("title", "Short Circuit 2").append("genres", Arrays.asList("ttt", "Comedy")),
                    new Document().append("title", "The Lego Frozen Movie2"));
            try {
                InsertManyResult result = collection.insertMany(movieList);
                logger.info("Inserted document ids: " + result.getInsertedIds());
            } catch (MongoException me) {
                logger.info("Unable to insert due to an error: " + me);
            }
        }
    }

}
