package com.lzj;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2021年05月06日 9:28
 */
public class Threadlocaldemo {
    public static class MyRunable implements Runnable {
        private  ThreadLocal threadLocal = new ThreadLocal();
        @Override
        public void run(){
            threadLocal.set((int)(Math.random()*100D));
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){

            }
            System.out.println(threadLocal.get());
        }
        public static void main(String[] args) {
            MyRunable sharedRunnableInstance = new MyRunable();
            Thread thread1 = new Thread(sharedRunnableInstance);
            Thread thread2 = new Thread(sharedRunnableInstance);
            thread1.start();
            thread2.start();
        }

    }
}
