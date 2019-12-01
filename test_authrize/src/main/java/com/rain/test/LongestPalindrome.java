package com.rain.test;

public class LongestPalindrome {


    /**
     * 动态规划的核心就是找转移方程
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        int longestPalindrome = 1;
        String longestPalindromeStr = s.substring(0, 1);
        boolean[][] dp = new boolean[len][len];
        // abcdedcba
        //   l   r
        // 如果 dp[l, r] = true 那么 dp[l + 1, r - 1] 也一定为 true
        // 关键在这里：[l + 1, r - 1] 一定至少有 2 个元素才有判断的必要
        // 因为如果 [l + 1, r - 1] 只有一个元素，不用判断，一定是回文串
        // 如果 [l + 1, r - 1] 表示的区间为空，不用判断，也一定是回文串
        // [l + 1, r - 1] 一定至少有 2 个元素 等价于 l + 1 < r - 1，即 r - l >  2

        // 写代码的时候这样写：如果 [l + 1,     r - 1]  的元素小于等于 1 个，即 r - l <=  2 ，就不用做判断了

        // 因为只有 1 个字符的情况在最开始做了判断
        // 左边界一定要比右边界小，因此右边界从 1 开始
        for (int r = 1; r < len; r++) {
            for (int l = 0; l < r; l++) {
                // 区间应该慢慢放大
                // 状态转移方程：如果头尾字符相等并且中间也是回文
                // 在头尾字符相等的前提下，

                if(s.charAt(l)==s.charAt(r) && (r-l<=2 || dp[l+1][r-1]) ){
                    dp[l][r] =true;
                    if(r-l+1>longestPalindrome){
                        longestPalindrome = r-l+1;
                        longestPalindromeStr =s.substring(l,r+1);
                    }
                }


                // 重点理解 or 的短路性质在这里的作用
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > longestPalindrome) {
                        longestPalindrome = r - l + 1;
                        longestPalindromeStr = s.substring(l, r + 1);
                    }
                }
            }
        }
        return longestPalindromeStr;

    }


    /**
     * 保利破解法
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        String res = "";
        if (s.length()<2){
            return s;
        }

        for(int i=0;i<s.length();i++){

            for (int j=i+1;j<=s.length();j++){
                String tmpRes = s.substring(i,j);
                StringBuilder sb = new StringBuilder(tmpRes).reverse();

                if(tmpRes.equals(sb.toString()) && res.length()<tmpRes.length()){ //说明是回文
                    res =tmpRes;
                }
            }

        }


        return  res;
    }

    /***
     * 没有添加 maxLen的过滤 然后导致 重复子串的判断 从而执行时间超长
     * @param s
     * @return
     */
    public static String longestPalindrome4(String s) {
        String res = "";
        if (s.length()<2){
            return s;
        }

        int paselen=0;

        for(int i=0;i<s.length();i++){

            for (int j=paselen+1;j<=s.length()-paselen;j++){
                String tmpRes = s.substring(i,j);
                StringBuilder sb = new StringBuilder(tmpRes).reverse();

                if(tmpRes.equals(sb.toString()) && res.length()<tmpRes.length()){ //说明是回文
                    res =tmpRes;
                    paselen =paselen+tmpRes.length();
                }
            }

        }


        return  res;
    }

    public static String longestPalindrome3(String s) {
        String res = "";
        if (s.length()<2){
            return s;
        }


        for(int i=0;i<s.length();i++){

            for (int j=i+1;j<=s.length();j++){
                String tmpRes = s.substring(i,j);

                if(isHuiWen(tmpRes) && res.length()<tmpRes.length()){ //说明是回文
                    res =tmpRes;
                }
            }

        }


        return  res;
    }

    public static String longestPalindrome5(String s) {
        int len = s.length();
        if (len<2){
            return s;
        }

        int makLen=1;
        String res = s.substring(0, 1);

        for(int i=0;i<len-1;i++){

            for (int j=i+1;j<len;j++){

                if(j-i+1>makLen &&  valid(  s,   i,   j) ){ //说明是回文 ，最少保证   有一个
                    String tmpRes = s.substring(i,j+1);
                    res =tmpRes;
                    makLen = j - i + 1;

                }

            }

        }


        return  res;
    }


    /**
     * 判断是不是回文：思想就是后边等于前边
     * @param s
     * @param left
     * @param right
     * @return
     */
    private static  boolean valid(String s, int left, int right) {
        // 验证子串 s[left, right] 是否为回文串
        while (left < right) {  // 左边等于右边结束
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }




    static   boolean isHuiWen(String str){
        int len = str.length()/2;

        int index=0;
        char[] chars = str.toCharArray();
        int sLen = chars.length-1;
        while (len>index){
            if(chars[index] != chars[sLen-index]){
                return false;
            }


            index++;

        }

        return true;
    }

    /**
     * 没有这个 意思 ，今天提交了多次代码，说明不了转为char 数组之后的效率更高
     *
     * 由char数组转为字符串单个比较的时候效率上优化了200ms
     *
     * 没有valid   简洁效率高
     * @param str
     * @return
     */
    static  boolean isHuiWen2(String str){

        int len = str.length()/2;

        int index=0;
        int sLen = str.length()-1;
        while (len>index){
            if(str.charAt(index) != str.charAt(sLen-index)){
                return false;
            }


            index++;

        }

        return true;

    }

    public static void main(String[] args) {
       String resut =  longestPalindrome5("babad");
        System.out.println("---》》"+resut);

        System.out.println(isHuiWen("aba"));
    }


    /**
     * 两个 回文判断的方法 差异开销的话 我想 下边的效率好久好在  没有多余的计算
     * 上边的有对  一半长度的计算
     * @param s
     * @param left
     * @param right
     * @return
     */
    public boolean isValid(String s,int left,int right){

        while (left<right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }

            left++;
            right--;



        }

        return true;
    }




}
