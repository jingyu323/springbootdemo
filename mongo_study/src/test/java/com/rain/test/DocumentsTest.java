package com.rain.test;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestClassOrder(ClassOrderer.DisplayName.class)
@DisplayName("mongodb study")
public class DocumentsTest {
    static String uri = "mongodb://192.168.20.131:27017";
    static MongoDatabase db = null;
    static MongoClient mongoClient = null;
    static Logger logger = LoggerFactory.getLogger(DocumentsTest.class);

    @Before
    public void before() {
        logger.info("ffff..");
        mongoClient = MongoClients.create(uri);
        MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
        for (String s : databaseNames) {

            logger.info(s);

        }
        db = mongoClient.getDatabase("raintest");
    }

    @Test
    public void testBson() {


    }

    @After
    public void after() {
        //6.关闭连接
        mongoClient.close();
    }


}
