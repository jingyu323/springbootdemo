本demo包含 mongo和es的练习代码



# mongo

1.问题获取集合之后如何判断该集合是否存在? 用listCollectionNames 查找集合的名字，如果名字存在则说明集合存在否则集合不存在，创建集合 for (String name :
db.listCollectionNames()) { System.out.println(name); if("test22222".equals(name)){ create =false; } } if(create){
db.createCollection("test22222"); System.out.println("集合创建成功"); }

2. 主键 插入文档时没有 _id 键，系统会自动创建 MongoDB中存储的文档必须有这个“_id”键。这个键的值可以是任意类型，默认是个ObjectId对象，每个文档有唯一的 _id ，确保集合中的每个文档都会被唯一标示。
   ObjectId是 _id 的默认类型，不同的机器都能用全局唯一的同种方法方便的生成。因为MongoDB初衷是用作分布式数据库，在多个服务器的分片环境中生成唯一标识符非常重要。
   ObjectId使用12字节的存储空间，是一个由24个十六进制数组组成的字符串。

   0-3位，共4位，是从标准纪元开始的时间戳，单位为秒。 时间戳与4-8这5位，提供了秒级别的唯一性。 由于时间戳在前，意味着ObjectId大致会按照插入的顺序排列。
   隐含了文档创建时间，绝大多数驱动也都会提供一个方法来从ObjectId中获取这个时间 4-6位，共3位，是所在主机的唯一标识符。通常是机器主机名的散列值。这样能确保不同主机生成不同的ObjectId，不产生冲突。
   7-8位，共2位，来自产生ObjectId的进程的进程标识符，为了确保在同一台机器上并发的多个进程产生的ObjectId是唯一的。
   这样前9个字节就保证了同一秒钟不同机器不同进程产生的ObjectId是唯一的。最后3个字节是一个自动增加的计数器，确保相同进程的同一秒产生的ID也是不同的。一秒钟最多运行每个进程拥有256^3（共16777216）个不同的ObjectId。

3.排序 在 MongoDB 中使用 sort() 方法对数据进行排序，sort() 方法可以通过参数指定排序的字段，并使用 1 和 -1 来指定排序的方式，其中 1 为升序排列，而 -1 是用于降序排列。

4.关键字 $push 添加元素。如果数组已经存在，会向已有的数组末尾加入一个元素，要是没有就创建一个新的数组。

$set与$unset 用来指定一个字段的值。如果这个字段不存在，则创建它。 $inc $inc 用来增加已有键的值，或者该键不存在那就创建一个。对于更新分析数据、因果关系、投票或者其他有变化数值的地方很方便。

upsert Set to true if a new document should be inserted if there are no matches to the query filter.

提供一个空的docment 则删除所有 If you provide an empty document, MongoDB matches all documents in the collection and deletes them

# es

1. 同一个id，内容会被第二次覆盖