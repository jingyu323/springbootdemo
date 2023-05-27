import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.bson.Document;

public class MongoDBJDBC {
    private final Logger logger = LoggerFactory.getLogger(MongoDBJDBC.class);
    public static void main(String[] args) {
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "192.168.182.142" , 27017 );

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("raintest");
            System.out.println("Connect to database successfully");


            //3.获取集合
            MongoCollection<Document> collection = mongoDatabase.getCollection("raintest");
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
