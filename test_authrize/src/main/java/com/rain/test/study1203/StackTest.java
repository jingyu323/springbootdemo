package com.rain.test.study1203;

import java.util.Stack;

/**
 * Stack
 *
 * pop()  弹出栈顶 元素 并返回这个元素
 * peek（） 查看栈顶元素不弹出
 */

public class StackTest {
    public static void main(String[] args) {
        Stack st  = new Stack();

      Object res =  st.push("ad");
        System.out.println(res);
        st.push("ad1");
        st.push("ads");

        Object ps =   st.pop();
        System.out.println(ps);
        System.out.println(st.size());

        Object pk = st.peek();
        System.out.println(pk);
        System.out.println(st.size());



    }
}
