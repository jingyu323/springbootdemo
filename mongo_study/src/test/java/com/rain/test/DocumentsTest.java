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
        document.put("name", "66");
        document.put("age", 33);
        document.put("sex", "女");
        document.put("wanju", "小汽车");
        collection.insertOne(document);

    }

    /**
     * updateMany 更新所有满足条件的
     * updateOne 永远只更新一条
     */
    @Test
    public void testUpdate() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
        Bson eq = Filters.eq("name", "9");
        UpdateResult updateOne = collection.updateMany(eq, new Document("$set", new Document("age", 99)));
        logger.info("updateOne=" + updateOne);
    }


    @Test
    public void testUpdateMany() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
        //修改多个
        //将年龄为100岁能匹配到的全部数据的姓名改为老八
        Bson and = Filters.eq("name", "老八");
        UpdateResult updateMany = collection.updateMany(and, new Document("$set", new Document("age", "100")));
        logger.info("updateOne=" + updateMany);
    }


    /***
     * $push 添加元素。如果数组已经存在，会向已有的数组末尾加入一个元素，要是没有就创建一个新的数组。
     */
    @Test
    public void testPush() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
        //修改多个
        //将年龄为100岁能匹配到的全部数据的姓名改为老八
        Bson and = Filters.eq("name", "老八");
        UpdateResult updateMany = collection.updateMany(and, new Document("$push", new Document("address", "test002")));
        logger.info("updateOne=" + updateMany);
    }


    @Test
    public void testQueryAll() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
//全查
        FindIterable<Document> find = collection.find();
//迭代器对象
        MongoCursor<Document> iterator = find.iterator();
//循环遍历
        while (iterator.hasNext()) {
            logger.info(String.valueOf(iterator.next()));
        }

    }

    @Test
    public void testQueryByFilter() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
//添加条件

        Bson bson = Filters.eq("name", "老八");
//带上条件查询
        FindIterable<Document> find = collection.find(bson);
//迭代器对象
        MongoCursor<Document> iterator = find.iterator();
//循环遍历
        while (iterator.hasNext()) {
            logger.info(String.valueOf(iterator.next()));
        }
    }

    @Test
    public void testQueryByMultiFilter() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
//添加条件

        Bson bson = Filters.and(Filters.eq("name", "9"), Filters.gte("age", 8));
//带上条件查询
        FindIterable<Document> find = collection.find(bson);
//迭代器对象
        MongoCursor<Document> iterator = find.iterator();
//循环遍历
        while (iterator.hasNext()) {
            logger.info(String.valueOf(iterator.next()));
        }
    }

    /**
     * 过滤出来所有name带9的
     */

    @Test
    public void testQueryByFuzyFilter() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
//添加条件

//        Bson bson = Filters.and(Filters.eq("name", "9"), Filters.gte("age", 8));
        Bson bson = Filters.regex("name", "9");

//带上条件查询
        FindIterable<Document> find = collection.find(bson);
//迭代器对象
        MongoCursor<Document> iterator = find.iterator();
//循环遍历
        while (iterator.hasNext()) {
            logger.info(String.valueOf(iterator.next()));
        }
    }

    @Test
    public void testQueryByLimitFilter() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
//添加条件

//        Bson bson = Filters.and(Filters.eq("name", "9"), Filters.gte("age", 8));
        Bson bson = Filters.regex("name", "9");

//带上条件查询
        FindIterable<Document> find = collection.find(bson).skip(0).limit(2);
//迭代器对象
        MongoCursor<Document> iterator = find.iterator();
//循环遍历
        while (iterator.hasNext()) {
            logger.info(String.valueOf(iterator.next()));
        }
    }

    /**
     * -1 降序 1 升序
     */
    @Test
    public void testQueryBySort() {
        MongoCollection<Document> collection = db.getCollection("coll_test2");
//添加条件

        Bson bson = new Document("age", 1);
//带上排序条件查询


//带上条件查询
        FindIterable<Document> find = collection.find().sort(bson);
//迭代器对象
        MongoCursor<Document> iterator = find.iterator();
//循环遍历
        while (iterator.hasNext()) {
            logger.info(String.valueOf(iterator.next()));
        }
    }


    @AfterEach
    public void after() {
        //6.关闭连接
        mongoClient.close();
    }


}
