package com.rain.study;

/**
 *
 */
public class Test {

    public static void main(String[] args) {
            String  first = "5545";
            String  sencond = "777777777";
            String  moreString = "";
            int [] result =  new int[sencond.length()];
            int maxLen = sencond.length();
            int firstLen = first.length();
            int index = 0;
            for(int start = maxLen -1 ;start >=0;start -- ){
                int firstInt = 0;
                int secInt = 0;
                if(firstLen - index > 0){
                    String firstStr = first.substring(firstLen-1-index,firstLen-index);
                    firstInt = Integer.valueOf(firstStr);
                }
                if(maxLen - index > 0){
                    String secStr = sencond.substring(maxLen-1-index,maxLen-index);
                    secInt = Integer.valueOf(secStr);
                }

                int sum = firstInt + secInt + result[start];
                // 取余数
                int  balance = sum % 10;
                result[start] = balance;
                int  mod = sum /10;
                if(mod > 0){
                    if(start-1 >=0){
                        result[start-1] = mod;
                    }else {
                        moreString = mod+"";
                    }
                }
                index++;
            }
            StringBuilder  sb = new StringBuilder(moreString);
            for(int in : result){
                if(in == 0){
                    continue;
                }
                sb.append(in);
            }

            System.out.println(sb.toString());
        }
    }

