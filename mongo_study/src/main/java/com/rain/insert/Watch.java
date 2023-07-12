package com.rain.insert;

import com.Constants;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.changestream.FullDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;

public class Watch {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(Constants.uri)) {

            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            List<Bson> pipeline = Arrays.asList(
                    Aggregates.match(
                            Filters.in("operationType",
                                    Arrays.asList("insert", "update"))));
            ChangeStreamIterable<Document> changeStream = database.watch(pipeline)
                    .fullDocument(FullDocument.UPDATE_LOOKUP);
            // variables referenced in a lambda must be final; final array gives us a mutable integer
            final int[] numberOfEvents = {0};
            changeStream.forEach(event -> {
                System.out.println("Received a change to the collection: " + event);
                if (++numberOfEvents[0] >= 2) {
                    System.exit(0);
                }
            });


        }
    }
}
