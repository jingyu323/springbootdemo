package com.rain.test.study1203;

import java.util.Stack;

public class LonggetValidPaarentes {

    public int longestValidParentheses(String s) {
        if (s== null){
            return 0;
        }

        int result =0;

        char[] chars = s.toCharArray();
        Stack<Integer> stacks =  new Stack<>();
        stacks.push(-1); //添加一个的目的在于 让栈第一个为右括号的时候不为空 弹出 来的时候不必报错
        for(int i=0;i<chars.length;i++){
            if(chars[i] == '('){
                stacks.push(i);
            }else{
                stacks.pop();
                if(stacks.isEmpty()){
                    stacks.push(i);
                }else {
                    result = Math.max(result,i-stacks.peek());// 栈中弹不出来的一般为多余的左括号 二栈顶的i就是对应的位置所以i-stack。peek 正好
                }

            }
        }

        return result;


    }

    public static void main(String[] args) {
        String ss = ")))()())";

       int s= new LonggetValidPaarentes().longestValidParentheses(ss);

        System.out.println(s);
    }
}
