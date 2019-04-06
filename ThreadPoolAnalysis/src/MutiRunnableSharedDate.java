public class MutiRunnableSharedDate implements  Runnable {



    private int ticket=100;//每个线程都拥有100张票
    private String name;
    MutiRunnableSharedDate(String name){
        this.name=name;
    }
    public void run(){
        while(ticket>0){
            System.out.println(ticket--+" is saled by "+name);
        }
    }




        public static void main(String[] args) {
            MutiRunnableSharedDate m=new MutiRunnableSharedDate("test ");
            Thread t1=new Thread(m);
            Thread t2=new Thread(m);
            Thread t3=new Thread(m);
            t1.start();
            t2.start();
            t3.start();
        }

}
