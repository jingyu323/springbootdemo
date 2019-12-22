package com.rain.test.test1212;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 给定数组 A，我们可以对其进行煎饼翻转：我们选择一些正整数 k <= A.length，然后反转 A 的前 k 个元素的顺序。我们要执行零次或多次煎饼翻转（按顺序一次接一次地进行）以完成对数组 A 的排序。
 *
 * 返回能使 A 排序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * A.length 范围内的有效答案都将被判断为正确。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[3,2,4,1]
 * 输出：[4,2,4,3]
 * 解释：
 * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 * 初始状态 A = [3, 2, 4, 1]
 * 第一次翻转后 (k=4): A = [1, 4, 2, 3]
 * 第二次翻转后 (k=2): A = [4, 1, 2, 3]
 * 第三次翻转后 (k=4): A = [3, 2, 1, 4]
 * 第四次翻转后 (k=3): A = [1, 2, 3, 4]，此时已完成排序。
 * 示例 2：
 *
 * 输入：[1,2,3]
 * 输出：[]
 * 解释：
 * 输入已经排序，因此不需要翻转任何内容。
 * 请注意，其他可能的答案，如[3，3]，也将被接受。
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 100
 * A[i] 是 [1, 2, ..., A.length] 的排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pancake-sorting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 煎饼翻转的理解：
 * 第一步：找到最大的位置，如果位置和 当前位置相等的话就直接  跳过则说明 位置正好
 * 第二步：如果不在则需要翻转把最大位置的上一个位置翻转到1的位置，直到所有的元素已经转变位置
 * 第三步：翻转最大元素到翻转队列(也就该次未排好排序元素)的队尾，然后依次翻转对应位置的元素，第二元素翻转到翻转队列的倒数第二的位置
 *
 */

public class PancakeSort {

    ArrayList<Integer> list = new ArrayList();
    public List<Integer> pancakeSort(int[] A) {
        int max;
        for (int i = A.length - 1; i >= 0; i--) {
            max = 0;
            //查找最大数位置
            for (int j = 0; j <= i; j++) {
                max = A[max] > A[j] ? max : j;
            }
            System.out.println(i);
            if (max == 0 && i != 0) {//最大数在0位置。翻转一次
                pancake(A, i);
            } else if (max != i) {//最大数不在0位置，先翻转到0位置，再翻转到目标位置。翻转两次
                pancake(A, max);
                pancake(A, i);
            }
        }
        return list;
    }
    // 翻转
    public void pancake(int[] A, int k) {
        list.add(k + 1);
        int i = 0;
        while (k > i) {
            swap(A, i, k);
            System.out.println(Arrays.toString(A));
            i++;
            k--;
        }
    }
    // 替换
    public void swap(int[] A, int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,4,1,6,8,5,7,9};
        List res  = new PancakeSort().pancakeSort(arr);
        System.out.println(res);

    }

}
