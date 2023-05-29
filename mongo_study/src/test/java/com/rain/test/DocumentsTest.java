package com.rain.test;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@DisplayName("我的第一个测试用例")
public class DocumentsTest {
    static String uri = "mongodb://192.168.20.131:27017";
    static MongoDatabase db = null;
    static Logger logger = LoggerFactory.getLogger(DocumentsTest.class);

    @BeforeAll
    public static void before() {
        MongoClient mongoClient = MongoClients.create(uri);
        MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
        for (String s : databaseNames) {

            logger.info(s);

        }
        db = mongoClient.getDatabase("raintest");
    }

    @Test
    public void testBson() {
        System.out.println("ssss");

    }


}
