import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * db -  db
 * table	collection
 *row	document
 *
 */


public class MongodbTestCollection {


    public static void main(String[] args) {

        String uri = "mongodb://192.168.182.142:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
            for (String s : databaseNames) {
                System.out.println(s);
            }
//            2.打开数据库test
            MongoDatabase db = mongoClient.getDatabase("raintest");
            MongoCollection<Document> collection = db.getCollection("test22222");

            System.out.println("name="+collection.getNamespace());

            if( collection.countDocuments() <= 0){
                db.createCollection("test22222");
                System.out.println("集合创建成功");
            }

            FindIterable<Document> documents = collection.find();
            System.out.println("document===================");
            //5.循环遍历
            for (Document doc1 : documents) {
                System.out.println(doc1);
            }



            // 检索集合
            MongoCollection<Document> tutorial = db.getCollection("test22222");
            // 删除集合
            tutorial.drop();
            System.out.println("集合删除成功");

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
