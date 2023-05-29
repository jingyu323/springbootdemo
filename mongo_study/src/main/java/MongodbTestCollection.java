import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.util.HashMap;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

/**
 * db -  db
 * table	collection
 *row	document
 *
 */


public class MongodbTestCollection {


    public static void main(String[] args) {

//        String uri = "mongodb://192.168.182.142:27017";

        String uri = "mongodb://192.168.20.131:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
            for (String s : databaseNames) {
                System.out.println(s);
            }
//            2.打开数据库test
            MongoDatabase db = mongoClient.getDatabase("raintest");
            MongoCollection<Document> collection = db.getCollection("test22222");

            System.out.println("name="+collection.getNamespace());


            System.out.println("name2===================");

            boolean create=true;
            System.out.println("name===================");
            for (String name : db.listCollectionNames()) {
                System.out.println(name);
                if("test22222".equals(name)){
                    create =false;
                }
            }
            if(create){
                db.createCollection("test22222");
                System.out.println("集合创建成功");
            }


            Map<String, Object> map = new HashMap<>();
//            map.put("_id", "20");
            map.put("name", "wangwu");
            map.put("age", 14);
            map.put("sex", 1);
            Document document = new Document(map);
            MongoCollection<Document> coll1 = db.getCollection("test22222");



            //向集合中插入文档
            coll1.insertOne(document);

            FindIterable<Document> documents = collection.find();
            System.out.println("document===================");
            //5.循环遍历
            for (Document doc1 : documents) {
                System.out.println(doc1);
            }



            // 检索集合
            MongoCollection<Document> tutorial = db.getCollection("test22222");
            // 删除集合
//            tutorial.drop();
            System.out.println("集合删除成功");


            System.out.println("-------------------");
            for (String name : db.listCollectionNames()) {
                System.out.println(name);

            }


            IndexOptions indexOptions = new IndexOptions().unique(true);
            String resultCreateIndex = collection.createIndex(Indexes.descending("age"), indexOptions);

            System.out.println(String.format("Index created: %s", resultCreateIndex));
            MongoCollection<Document> collection1 = db.getCollection("test22222");
            System.out.println("name="+collection1.getNamespace());
            FindIterable<Document> documents1 = collection1.find();
            //5.循环遍历
            for (Document doc1 : documents1) {
                System.out.println(doc1);
            }

        }



    }
}
