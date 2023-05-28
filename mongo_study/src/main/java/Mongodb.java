

import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;

import java.util.List;


public class Mongodb {
    public static void mongoQueryAll() {
//



        //1.创建链接
        MongoClient client = new MongoClient( "192.168.182.142" , 27017  );

        MongoIterable<String> databaseNames = client.listDatabaseNames();
        for (String s : databaseNames) {
            System.out.println(s);
        }


        //2.打开数据库test
//        MongoDatabase db = client.getDatabase("raintest");
//        System.out.println("sss");
//        System.out.println("Connect to database successfully");
//        db.createCollection("test22222");
//        System.out.println("集合创建成功");
    }

    public static void main(String[] args) {
        mongoQueryAll();
    }
}
