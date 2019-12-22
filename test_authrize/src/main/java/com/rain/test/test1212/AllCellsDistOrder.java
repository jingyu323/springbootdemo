package com.rain.test.test1212;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AllCellsDistOrder {

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        Map<Integer, List>  temValues = new TreeMap<>();
        for (int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                int dis = Math.abs(r0-i)+Math.abs(c0-j);
                if(temValues.get(temValues).isEmpty()){
                    List<String> arr = new ArrayList();

                    arr.add(i+","+j);

                }else{
                    temValues.get(temValues).add("["+i+","+j+"]");
                }

            }
        }
        int[][] res = new int[R][C];

        for (Map.Entry en : temValues.entrySet()){



        }
        return res;

    }


    public static void main(String[] args) {

    }
}
