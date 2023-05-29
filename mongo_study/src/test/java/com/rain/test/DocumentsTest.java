package com.rain.test;


import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.and;

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
        mongoClient = MongoClients.create(uri);
        MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
        for (String s : databaseNames) {
            logger.info(s);
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

    @Test
    public void testAdd() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
        Document document = new Document();
        document.put("name","老八");
        document.put("age",88);
        document.put("sex","女");
        document.put("wanju","小汽车");
        collection.insertOne(document);

    }

    /**
     * updateMany 更新所有满足条件的
     * updateOne 永远只更新一条
     */
 @Test
    public void testUpdate() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
        Bson eq = Filters.eq("name", "老八");
        UpdateResult updateOne = collection.updateMany(eq,new Document("$set",new Document("age","20")));
        logger.info("updateOne="+updateOne);
    }

    @Test
    public void testUpdateMany() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
        //修改多个
//将年龄为100岁能匹配到的全部数据的姓名改为老八
        Bson and = Filters.eq("name", "老八");
        UpdateResult updateMany = collection.updateMany(and, new Document("$inc", new Document("age", "-1")));
        logger.info("updateOne="+updateMany);
    }
    @AfterEach
    public void after() {
        //6.关闭连接
        mongoClient.close();
    }


}
