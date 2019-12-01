package com.rain.test;

public class FindDerangement {

    private int MOD = 1000_000_000 + 7;
    public int findDerangement(int n) {

        long[] dp = new long[n+1];

        if(n==1){
            return  0;
        }

        dp[1] =0;
        dp[2] =1;


        for (int i=3;i<=n;i++){
            dp[i] = (n-1)*(dp[n-1]+dp[n-2]);

            dp[i] %= MOD;
        }

        return (int)dp[n];


    }
}
