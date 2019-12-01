package com.rain.test;

public class IsSymmetric {

    public boolean isSymmetric(TreeNode root) {
return  isMirror(root,root);
    }

    public boolean isMirror(TreeNode left,TreeNode right){
        if(left == null && right == null){
         return  true;
        }
        if(left == null || right == null){
         return  true;
        }

        return left.val == right.val
                && isMirror(right.left,left.right)
                && isMirror(right.right,left.left);

    }
}
