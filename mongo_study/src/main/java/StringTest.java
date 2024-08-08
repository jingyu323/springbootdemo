public class StringTest {

    public static void main(String[] args) {


        int value = 0x1234; // 示例整数
        System.out.println(value);

        System.out.println(byte2Hex(hex2LowHighByte(value)).toUpperCase());


    }
    public static byte[] hex2LowHighByte(long value){
        byte a[]=new byte[2];//双字节
        a[0]=(byte)(value&0xff); //获得低位字节
        a[1]=(byte)(value>>>8);//获得高位字节
        return a;
    }

    /**
     * byte数组转为十六进制字符串
     *
     * @param bytes
     * @return
     */
    public static String byte2Hex(byte[] bytes) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
