
import com.mongodb.client.*;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;


public class MongoDBJDBC {
     public static void main(String[] args) {
        try{
            // 连接到 mongodb 服务
//            MongoClient mongoClient = new MongoClient( "192.168.182.142" , 27017 );
            String uri = "mongodb://192.168.182.142:27017";
            MongoClient mongoClient = MongoClients.create(uri);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("collection_test");

            System.out.println( mongoDatabase.getCollection("coll_test1"));
            if(mongoDatabase.getCollection("coll_test1") == null ){
                mongoDatabase.createCollection("coll_test1");
            }

            MongoCollection<Document> coll1 = mongoDatabase.getCollection("coll_test1");

            // 创建文档
            Map<String, Object> map = new HashMap<>();
            map.put("_id", "13");
            map.put("name", "zhaoyun");
            map.put("age", 18);
            map.put("sex", 1);
            Document document = new Document(map);
            //向集合中插入文档
            coll1.insertOne(document);
            System.out.println(coll1.countDocuments());

            //3.获取集合
            MongoCollection<Document> collection = mongoDatabase.getCollection("coll_test1");
            //4.查询获取文档集合
            FindIterable<Document> documents = collection.find();
            //5.循环遍历
            for (Document doc : documents) {
                System.out.println(doc);
            }
            //6.关闭连接
            mongoClient.close();

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
