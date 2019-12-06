package com.rain.test.study1203;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
}
public class IsSameTree {
    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        System.out.println("先序遍历：");
        preOrderTraverse(p);
        System.out.println();

        System.out.println("中序遍历：");
        inOrderTraverse(p);
        System.out.println();

        System.out.println("后序遍历：");
        postOrderTraverse(p);
        System.out.println();
    }

    static void preOrderTraverse(TreeNode node) {
        if(node == null)
            return;
        System.out.print(node.val +" ");
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    static void inOrderTraverse(TreeNode node) {
        if(node == null)
            return;
        inOrderTraverse(node.left);
        System.out.print(node.val + " ");
        inOrderTraverse(node.right);
    }

    static void postOrderTraverse(TreeNode node) {
        if(node == null)
            return;
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.print(node.val+" ");

    }
}
