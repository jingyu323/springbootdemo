package com.rain.test;


import com.mongodb.client.*;
import org.bson.Document;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@TestClassOrder(ClassOrderer.DisplayName.class)
@DisplayName("mongodb study")
public class DocumentsTest {
                String uri = "mongodb://192.168.182.142:27017";
//    static String uri = "mongodb://192.168.20.131:27017";
    static MongoDatabase db = null;
    static MongoClient mongoClient = null;
    private final static Logger logger = LoggerFactory.getLogger(DocumentsTest.class);

    @BeforeEach
    public void before() {
        logger.info("ffff..");
        mongoClient = MongoClients.create(uri);
        MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
        for (String s : databaseNames) {

            logger.info(s);
//            System.out.println(s);

        }
        db = mongoClient.getDatabase("raintest");
    }

    @Test
    public void testGetAllCollection() {

        MongoCollection<Document> collection = db.getCollection("coll_test1");
        List<Document> indexes = new ArrayList<>();
        logger.info("index size is:" + indexes.size());
        collection.listIndexes().into(indexes);
        indexes.forEach(idx -> System.out.println(idx.toJson()));
    }

    @AfterEach
    public void after() {
        //6.关闭连接
        mongoClient.close();
    }


}
