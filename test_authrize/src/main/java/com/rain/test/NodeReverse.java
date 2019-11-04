package com.rain.test;

public class NodeReverse {

    public static void main(String[] args) {

            // 1->2  3->4   43 +21  = 64    4->6
                       ListNode l1 = new ListNode(2)  ;
                       ListNode l2 = new ListNode(4)  ;
                       ListNode l3 = new ListNode(3)  ;
                       ListNode l4 = new ListNode(5)  ;
                       ListNode l5 = new ListNode(6)  ;
                       ListNode l6 = new ListNode(4)  ;
                       l1.next=l2;
                       l2.next=l3;
                       l4.next=l5;
                       l5.next=l6;


                       ListNode node = addTwoNumbers( l1,  l4);

        printNode(node);



    }

    public static   ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int first =paseNode(l1,0,0) ;

        int sec =paseNode(l2,0,0) ;

        int res =   first+   sec;


       ListNode resNoe = buildResult ( res,null) ;


        return  resNoe;


    }

    public  static int  paseNode(ListNode node,int deep,int result)  {
            result = result + node.val* (int)Math.pow(10, deep);
            if(node.next!=null){
                      ListNode next = node.next;
                      node.next=null;
                return paseNode(next, deep+1,result) ;
            }
           return result;

    }

    public static  ListNode buildResult (int res,ListNode pre )   {
        // 64  4->6    807
        int  sec =(int)Math.pow(10, String.valueOf(res).length()-1);
           int leg = res /sec;
           ListNode node = new ListNode(leg);
           if(pre !=null ){
                 node.next = pre;
           }
           int left =  res -   leg* sec;

           ListNode misnode =null;
           if(String.valueOf(res).length() >String.valueOf(left).length() ){
               int mis  =String.valueOf(res).length()-1 -    String.valueOf(left).length();
               if(mis==1){
                   misnode = new ListNode(0);
                   misnode.next =node;
               }else{
                   misnode =   buildEmptyNode(mis ,node)  ;
               }

           }
           if(left > 10){
            return  buildResult(left,node);

           }





            ListNode last = new ListNode(left);
              if(misnode !=null){
                    last.next = misnode;

              } else{
                  last.next = node;
              }
           return  last;


    }

    public  static  void  printNode(ListNode node){
        System.out.println(node.val+"--->");
        if (node.next !=null){
                   printNode(node.next);
        }
    }

    public  static ListNode  buildEmptyNode(int leng ,ListNode preNode){
         ListNode newNo = new ListNode(0);

                    newNo.next =preNode;
         if(leng-1 >1){
                 return  buildEmptyNode(leng-1,newNo) ;
         }

         return   newNo;

    }


}

