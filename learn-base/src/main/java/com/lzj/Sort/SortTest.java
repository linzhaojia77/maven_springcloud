package com.lzj.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2021年06月02日 15:00
 */
public class SortTest {
    public static void main(String[] args) {
        Sort sort = new Sort();
        int [] a = {12,8,9,78,255,66,74,5,14,23,64,177,82,5,22,255,99};
        for(int avalue:a){
            System.out.print(avalue+" ");
        }

        //插入排序
        int [] b = a.clone();
        sort.insertSort(b);
        System.out.println();
        for(int avalue:b){
            System.out.print(avalue+" ");
        }
        //选择排序
        int [] c = a.clone();
        sort.selectSort(c);
        System.out.println("插入排序");
        for(int avalue:c){
            System.out.print(avalue+" ");
        }
        //冒泡排序
        int [] d = a.clone();
        sort.bubbleSort(d);
        System.out.println("选择排序");
        for(int avalue:d){
            System.out.print(avalue+" ");
        }
        //快速排序
        int [] e = a.clone();
        sort.quickSort(e,0,e.length-1);
        System.out.println("冒泡排序");
        for(int avalue:e){
            System.out.print(avalue+" ");
        }
        //归并排序
        int [] f = a.clone();
        sort.mergeSort(f,0,f.length-1);
        System.out.println("快速排序");
        for(int avalue:f){
            System.out.print(avalue+" ");
        }
        //堆排序
        int [] g = a.clone();
        sort.heapSort(g);
        System.out.println("归并排序");
        for(int avalue:g){
            System.out.print(avalue+" ");
        }
        //希尔排序
        int [] h = a.clone();
        sort.sheelSort(h);
        System.out.println("堆排序");
        for(int avalue:h){
            System.out.print(avalue+" ");
        }
        //基数排序
        int [] i = a.clone();
        sort.baseSort(i);
        System.out.println("希尔排序");
        for(int avalue:i){
            System.out.print(avalue+" ");
        }
        System.out.print("基数排序");
    }
}
