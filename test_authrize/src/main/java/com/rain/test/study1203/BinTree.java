package com.rain.test.study1203;

import java.util.ArrayList;
import java.util.List;

public class BinTree {

    private class Node{
        private String data;
        private Node lchid;//定义指向左子树的指针
        private Node rchild;//定义指向右子树的指针
        public Node(String data,Node lchild,Node rchild){
            this.data=data;
            this.lchid=lchild;
            this.rchild=rchild;
        }
    }

    private Node root;
    private List<Node> list=new ArrayList<Node>();
    public BinTree(){
        Node x=new Node("X",null,null);
        Node y=new Node("Y",null,null);
        Node d=new Node("d",x,y);
        Node e=new Node("e",null,null);
        Node f=new Node("f",null,null);
        Node c=new Node("c",e,f);
        Node b=new Node("b",d,null);
        Node a=new Node("a",b,c);
        root =a;//一定要将树根进行赋值
    }

    public void preOrder(Node node){
        if(node == null){
            return;
        }

        list.add(node);

        if(node.lchid!=null){
            preOrder(node.lchid);
        }

        if(node.rchild !=null){
            preOrder(node.rchild);
        }

    }


    public  void  midOrder(Node root){
        if(root == null){
            return;
        }

        if(root.lchid!=null){
            midOrder(root.lchid);
        }

        list.add(root);

        if(root.rchild!=null){
            midOrder(root.rchild);
        }

    }

    /**
     * 对该二叉树进行后序遍历 结果存储到list中
     */
    public void postOrder(Node node)
    {
        if(node.lchid!=null){
            postOrder(node.lchid);
        }
        if(node.rchild!=null){
            postOrder(node.rchild);
        }
        list.add(node);

    }


}
