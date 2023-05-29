import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class MongoDBJDBC {
    private final static Logger logger = LoggerFactory.getLogger(MongoDBJDBC.class);

    public static void main(String[] args) {
        try {
            logger.info("sss..");
            // 连接到 mongodb 服务
//            MongoClient mongoClient = new MongoClient( "192.168.182.142" , 27017 );
//            String uri = "mongodb://192.168.182.142:27017";
            String uri = "mongodb://192.168.20.131:27017";
            MongoClient mongoClient = MongoClients.create(uri);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("collection_test");

            System.out.println(mongoDatabase.getCollection("coll_test1"));
            if (mongoDatabase.getCollection("coll_test1") == null) {
                mongoDatabase.createCollection("coll_test1");
            }

            MongoCollection<Document> coll1 = mongoDatabase.getCollection("coll_test1");

            for (int i = 0; i < 10000000; i++) {
                // 创建文档
                Map<String, Object> map = new HashMap<>();
                map.put("_id", "id" + i);
                map.put("name", "wangwu");
                map.put("age", i);
                map.put("sex", 1);
                Document document = new Document(map);
                //向集合中插入文档
                coll1.insertOne(document);
                System.out.println(coll1.countDocuments());
            }


            //3.获取集合
            MongoCollection<Document> collection = mongoDatabase.getCollection("coll_test1");
            //4.查询获取文档集合
            FindIterable<Document> documents = collection.find();
            //5.循环遍历
            for (Document doc : documents) {
                System.out.println(doc);
            }


            //单条件查询
            Bson eq = Filters.eq("_id", "13");
            FindIterable<Document> find1 = collection.find(eq);
            //多条件查询
            Bson bson = Filters.and(
                    Filters.eq("_id", "14")
            );
            FindIterable<Document> find2 = collection.find(bson);


            for (Document doc : find1) {
                System.out.println(doc);
            }

            System.out.println("find2 ========================");
            for (Document doc : find2) {
                System.out.println(doc);
            }

            Bson regex = Filters.regex("name", "w");// （开头）：^桑 / （结尾）：桑$
            FindIterable<Document> find3 = collection.find(regex);
            //分页
            FindIterable<Document> find4 = collection.find().limit(3);
            //排序
            Document doc = new Document("age", -1);
            FindIterable<Document> find5 = collection.find().sort(doc);
            System.out.println("find2 ========================");
            for (Document doc1 : find4) {
                System.out.println(doc1);
            }
            System.out.println("find3 ========================");
            for (Document doc1 : find3) {
                System.out.println(doc1);
            }
            System.out.println("find5 ========================");
            for (Document doc1 : find5) {
                System.out.println(doc1);
            }

            Bson filter = Filters.eq("_id", "14");
            //指定修改的更新文档
            Document updateDocument = new Document("$set", new Document("age", 100).append("name", "安琪拉"));
            UpdateResult updateResult = coll1.updateOne(filter, updateDocument);
            if (updateResult.getModifiedCount() == 0) {
                System.out.println(false);
            } else {
                System.out.println(true);
                System.out.println(updateResult.getModifiedCount()); //1
            }

            System.out.println("find ========================");
            //全查
            FindIterable<Document> find = collection.find();
            for (Document doc2 : find) {
                System.out.println(doc2);
            }

            //6.关闭连接
            mongoClient.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
