server.1=192.168.93.41:2888:3888
server.2=192.168.93.42:2888:3888
server.3=192.168.93.27:2888:3888

/usr/local/zookeeper/data
echo 1 >  /usr/local/zookeeper/data/myid

export ZK_HOME=/usr/local/zookeeper
export PATH=$PATH:$ZK_HOME/bin

zookeeper.connect=192.168.93.41:2181,192.168.93.42:2181,192.168.93.27:2181

/usr/local/zookeeper/bin && ./zkServer.sh  start

 ./kafka-topics.sh --list --bootstrap-server 192.168.93.42:9092
log.dir=/var/log/kafka/logs

启动kafka
cd /usr/local/kafka/bin &&   ./kafka-server-start.sh -daemon ../config/server.properties

./kafka-topics.sh  --bootstrap-server  192.168.93.41:9092,192.168.93.42:9092,192.168.93.27:9092 --create --topic topic01 --partitions 2 --replication-factor 1
./kafka-console-consumer.sh --bootstrap-server  192.168.93.41:9092,192.168.93.42:9092,192.168.93.27:9092 --from-beginning --topic  raintest

  
 创建topics
 ./kafka-topics.sh --create --bootstrap-server 192.168.93.41:9092,192.168.93.42:9092,192.168.93.27:9092 --partitions 3 --replication-factor 1 --topic raintest
 
 
 查看topic 列表
 
 ./kafka-topics.sh --list --bootstrap-server 192.168.99.165:9092
  
 创建topics
 
 ./kafka-topics.sh --bootstrap-server 192.168.99.165:9092 --create --partitions 1 --replication-factor 3 --topic first
 
 启动生产者
 ./kafka-console-producer.sh --bootstrap-server 192.168.93.41:9092,192.168.93.42:9092,192.168.93.27:9092 --topic raintest
 
 
 查看指定的主题
 ./kafka-topics.sh --describe --bootstrap-server  192.168.99.175:9092
 ./kafka-topics.sh --describe --bootstrap-server  192.168.99.175:9092  --topic raintest

zookeeper.connect=192.168.93.41:2181,192.168.93.42:2181,192.168.93.27:2181
 ./kafka-topics.sh --list --bootstrap-server 192.168.93.42:9092
 
 在任意一台节点上执行查询命令都行
  ./kafka-topics.sh --list --bootstrap-server 192.168.93.42:9092
  
  创建topic 用9092 kafka的端口
  
./kafka-topics.sh --create --bootstrap-server 192.168.93.41:9092,192.168.93.42:9092,192.168.93.27:9092 --partitions 3 --replication-factor 2 --topic raintest
 
1. server.config 别忘了修改brokerid


## 错误1 hile executing topic command : Timed out waiting for a node assignment. Call: createTopics
检查：监听 plain端口修改没有
2.命令端口是否为kafka配置端口

## 2. kafka使用笔记

### 2.1 基础概念
#### 2.1.1 Group
Consumer Group 
### 2.2 遇到的问题

