package com.rain.insert;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

public class Find {
    public static void main(String[] args) {
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb://192.168.182.142:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
//            展现所有要查询的结果 字段
            Bson projectionFields = Projections.fields(
                    Projections.excludeId(),   // 排除id字段
                    Projections.include("title", "Cool Runnin"),
                    Projections.include("runtime", "w"));
            MongoCursor<Document> cursor = collection.find(gt("runtime", 15))
                    .projection(projectionFields)
                    .sort(Sorts.descending("title")).iterator();
            try {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }
            } finally {
                cursor.close();
            }
        }
    }
}
