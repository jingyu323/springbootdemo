import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


public class Mongodb {
    public static void mongoQueryAll() {
        String uri = "mongodb://192.168.182.142:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            Document doc = collection.find(eq("title", "Back to the Future")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
            MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
            for (String s : databaseNames) {
                System.out.println(s);
            }
//            2.打开数据库test
            MongoDatabase db = mongoClient.getDatabase("raintest");

            db.createCollection("test22222");
            System.out.println("集合创建成功");
        }
    }

    public static void main(String[] args) {
        mongoQueryAll();
    }
}
