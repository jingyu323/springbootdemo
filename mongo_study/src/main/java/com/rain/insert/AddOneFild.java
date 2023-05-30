package com.rain.insert;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.ne;

public class AddOneFild {
    private final static Logger logger = LoggerFactory.getLogger(AddOneFild.class);

    public static void main(String[] args) {
        String uri = "mongodb://192.168.182.142:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            Bson query = ne("title", ""); // 利用title不为空过滤出来所有记录
            Bson updates = Updates.combine(
                    Updates.set("num_mflix_comments", 60), // 需要新增的字段
                    Updates.addToSet("genres", "Frequently Discussed"),
                    Updates.currentTimestamp("lastUpdated"));

            UpdateOptions options = new UpdateOptions().upsert(true);
            try {
                UpdateResult result = collection.updateMany(query, updates, options);
                logger.info("Modified document count: " + result.getModifiedCount());
            } catch (MongoException me) {
                logger.info("Unable to update due to an error: " + me);
            }

        }

    }
}
