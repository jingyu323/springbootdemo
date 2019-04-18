package com.rain.study.test;

public class NodeTest {
    String data;
    NodeTest pre;
    NodeTest next;

    public static void main(String[] args) {

        NodeTest t1 = new  NodeTest();
        NodeTest t2 = new  NodeTest();
        NodeTest t3 = new  NodeTest();
        NodeTest t4 = new  NodeTest();
        t1.setPre(t2);
        t1.setData("this is  t1");
        t1.setNext(t3);

        t2.setNext(t1);
        t2.setData("this is t2");

        t3.setPre(t1);
        t3.setData("this is t3");
        t3.setNext(t4);

        t4.setPre(t3);
        t4.setData("this is t4");

        new NodeTest().getAllNodeDate(t1,null);

    }


    /**
     * 必须区分当前节点位置，还要区分向前向后走的问题 所以必须添加一个
     * @param node
     * @param curIndex
     */
    public  void getAllNodeDate(NodeTest node,String curIndex){

         if(node !=null ){

             if(curIndex==null){
                 System.out.println("node = [" + node.getData() + "]");

                 if(node.getPre()!=null ){
                     getAllNodeDate(node.getPre(),"Y");
                 }
                 if(node.getNext()!=null ){
                     getAllNodeDate(node.getNext(),"N");
                 }
             }else{
                 System.out.println("node = [" + node.getData() + "]");
             }



             if(node.getPre()!=null && "Y".equals(curIndex)){
                 getAllNodeDate(node.getPre(),"Y");
             }else{
             if(node.getNext() !=null &&"N".equals(curIndex)){
                 getAllNodeDate(node.getNext(),"N");
             }
         }
         }

    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public NodeTest getPre() {
        return pre;
    }

    public void setPre(NodeTest pre) {
        this.pre = pre;
    }

    public NodeTest getNext() {
        return next;
    }

    public void setNext(NodeTest next) {
        this.next = next;
    }


}
