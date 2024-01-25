public class    VolatileTest  extends Thread {


    /**
     * 主要说的是 线程会复制内存中的数据 为自己的副本
     */
    volatile   boolean flag = false;
    int i = 0;

    public void run() {
        while (!flag) {
            i++;
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileTest vt = new VolatileTest();
        vt.start();
        Thread.sleep(2000);
        vt.flag = true;
        System.out.println("stope" + vt.i);
    }
}
