package com.lzj;


public class VectorDemo
{
    public static void main(String[] args) {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while(threadGroup.getParent()!=null){
            threadGroup = threadGroup.getParent();
            System.out.println(threadGroup.getName());
        }
        int totalThread = threadGroup.activeCount();
        System.out.println(totalThread);
    }
}
