package com.lzj;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2021年05月12日 9:29
 */

public class CASTest {
    static AtomicInteger a = new AtomicInteger(0);
    static AtomicInteger b = new AtomicInteger(0);
    public static void main(String args[]) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int count = 0;
                    while (count < 100) {
                        int value = a.addAndGet(1);
                        int value2 = b.addAndGet(2);
                        System.out.println("in thread1 a = " + value+","+value2);
                        count++;
                    }
                } catch (Exception e) {

                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int count = 0;
                    while (count < 100) {
                        int value = a.addAndGet(1);
                        int value2 = b.addAndGet(2);
                        System.out.println("in thread2 a = " + value+","+value2);
                        count++;
                    }
                } catch (Exception e) {

                }
            }
        });
        t2.start();
        t1.start();
    }

}