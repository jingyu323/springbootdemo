package com.rain.test;

public class TestMainInteger {
    public static void main(String[] args) {




        Long l = Long.valueOf("998898");
        Long l1 = Long.valueOf("1000");
        Long l2= Long.valueOf("888888");

        System.out.println(l/1000.0);

       String res = formateDouble(l/1000.0);

        System.out.println(res);

        String ss ="E:\\srv\\data\\teds_test_manage\\dataLabelRootPath\\sdgsdgsdg\\第二次过车构造-重庆第十一次构造-0521_CRH2A_0\\第二次过车构造-重庆第十一次构造-0521_CRH2A_0_2385\\1_10X1001.jpg".replaceAll("\\\\", "/");


        System.out.println( ss.split("X")[1].substring(1, 4));
        System.out.println( ss.split("X")[1].substring(0, 1));

        String trainDir = "第二次过车构造-重庆第十一次构造-0521_CRH2A_0";

        System.out.println(ss.substring(ss.indexOf(trainDir) ,ss.lastIndexOf("/")));


        System.out.println("20251117190700".substring(0,8));
        System.out.println("20251117190700".substring(8));


        System.out.println( String.format("%04d", 123));
        System.out.println( String.format("%04d", 123234324));
        System.out.println( String.format("%04d", 1253353).substring("1253353".length()-4));


        System.out.println(3771/10000.0*800.0);

        System.out.println(9833*1024/10000.0);


        System.out.println(3771*800.0/10000.0);
        System.out.println(9833*1024/10000.0);

        System.out.println(getRealSizeH(3771,1024));
        System.out.println(getRealSizeW(9471,700));



    }


    private static  int getRealSizeW(Integer width,Integer ow){
        int res = (int) (width*ow/10000.0);
        return res;
    }

    private static  int getRealSizeX(Integer width,Integer ow){
        int res = (int) (width/10000.0%ow);
        return res;
    }
    private static String formateDouble(double number) {
        String formattedNumber = String.format("%.2f", number);
        return formattedNumber;
    }
    /**
     * 还原国科大的计算高度（国科大模型计算方式  图像原始with /800 *10000 的结果值作为返回值）
     * @param height
     * @return
     */
    private static  int getRealSizeH(Integer height,int h){
        int res = (int) (height*h/10000.0);
        return res;
    }

}
