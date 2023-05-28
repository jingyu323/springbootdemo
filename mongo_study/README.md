# mongo
1.问题获取集合之后如何判断该集合是否存在?
用listCollectionNames 查找集合的名字，如果名字存在则说明集合存在否则集合不存在，创建集合
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