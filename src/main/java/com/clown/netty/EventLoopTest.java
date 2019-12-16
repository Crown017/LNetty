package com.clown.netty;


public class EventLoopTest {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();

//        FutureTask
    }

   class Mythread  extends Thread{
       @Override
       public void run() {
           System.out.println("runing");
       }
   }
}
