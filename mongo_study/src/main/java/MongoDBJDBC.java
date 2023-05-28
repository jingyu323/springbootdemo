
import com.mongodb.client.*;
import org.bson.Document;


public class MongoDBJDBC {
     public static void main(String[] args) {
        try{
            // 连接到 mongodb 服务
//            MongoClient mongoClient = new MongoClient( "192.168.182.142" , 27017 );
            String uri = "mongodb://192.168.182.142:27017";
            MongoClient mongoClient = MongoClients.create(uri);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("admin");
            System.out.println("Connect to database successfully");

            //3.获取集合
            MongoCollection<Document> collection = mongoDatabase.getCollection("admin");
            //4.查询获取文档集合
            FindIterable<Document> documents = collection.find();
            //5.循环遍历
            for (Document document : documents) {
                System.out.println(document);
            }
            //6.关闭连接
            mongoClient.close();

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
