

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class Mongodb {
    public static void mongoQueryAll() {
        //1.创建链接
        MongoClient client = new MongoClient( "192.168.182.142" , 27017 );
        //2.打开数据库test
        MongoDatabase db = client.getDatabase("raintest");
        System.out.println("sss");
        System.out.println("Connect to database successfully");
        db.createCollection("test22222");
        System.out.println("集合创建成功");
    }

    public static void main(String[] args) {
        mongoQueryAll();
    }
}
