package com.rain.test.dbindex;



import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

/**
 * 添加  connect.setAutoCommit(false); 以及 rewriteBatchedStatements=true 提高插入效率
 */
public class MilionsData {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        Driver driver = new Driver();

        //2.得到连接
        //(1) jdbc:mysql:// 规定好表示协议，通过 jdbc 的方式连接 mysql
        //(2) localhost 主机，可以是 ip 地址
        //(3) 3306 表示 mysql 监听的端口
        //(4) db02 连接到 mysql dbms 的哪个数据库
        //(5) mysql 的连接本质就是前面学过的 socket 127.0.0.1是连接本地数据库，也可以填localhost，如果是连云服务器上的mysql，填服务器公网地址
        String url = "jdbc:mysql://localhost:3306/rain_test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&rewriteBatchedStatements=true";
        //将 用户名和密码放入到 Properties 对象
        Properties properties = new Properties();
        //说明 user 和 password 是规定好，后面的值根据实际情况写
        properties.setProperty("user", "root");//数据库用户
        properties.setProperty("password", "root");//数据库密码

        Connection connect = driver.connect(url, properties);
        long startTime = System.currentTimeMillis();
        connect.setAutoCommit(false);
        Statement statement = connect.createStatement();

        for (int i=0;i<10000000;i++){
            String  devcode = "devcode101"+i;
            String  traincode = "traincode121"+i;
            String devicesql = "insert into device(dev_code,dev_state,net_state,last_online,firmware_version,create_time,flag) values( '"+ devcode+"', '在线','Y', now(), '2.4.6',now(),'1')";
            String deviceTrainsql = "insert into train(train_code,train_station,create_time,flag) values('"+traincode+"', '车站',now(), '1')";
            String trainsql = "insert into train_device(train_code,dev_code) values( '"+traincode+"', '"+devcode+"')";

            statement.addBatch(devicesql);
            statement.addBatch(deviceTrainsql);
            statement.addBatch(trainsql);


            if (i % 50000 == 0) {
                statement.executeBatch();
                System.out.println("成功插入第 "+ i+" 条数据"+ new Date());
            }

        }
        statement.executeBatch();
        long spendTime = System.currentTimeMillis()-startTime;
        System.out.println("成功插入 100000000 万条数据,耗时："+spendTime+"毫秒");
        connect.commit();
        //4.释放资源
        statement.close();
        connect.close();




    }


}
