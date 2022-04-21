package com.lzj;

public class lazysingleton {
    public static void main(String[] args) {
//        lazySingletonDemo instance1 = lazySingletonDemo.getInstance();
//        lazySingletonDemo instance2 = lazySingletonDemo.getInstance();
//        System.out.println(instance1==instance2);
        new Thread(()->{
            lazySingletonDemo instance3 = lazySingletonDemo.getInstance();
            System.out.println(instance3);
        }).start();
        new Thread(()->{
            lazySingletonDemo instance4 = lazySingletonDemo.getInstance();
            System.out.println(instance4);
        }).start();


    }
}
class lazySingletonDemo{
    //volatile防止重排序的问题，即以免我们拿到还未被初始化的单例；
    private volatile static lazySingletonDemo instance;
    private lazySingletonDemo(){

    }
    //多线程通过锁防止创建多个实例
    public static  lazySingletonDemo getInstance(){
        if(instance ==null){
            synchronized (lazySingletonDemo.class) {
                if(instance==null){
                instance = new lazySingletonDemo();
            }}
        }
        return instance;
    }
}