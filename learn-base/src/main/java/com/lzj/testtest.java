package com.lzj;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2021年06月15日 16:03
 *
 */
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class testtest {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();

        calendar.setTime(new Date());

        calendar.add(calendar.DATE,-1);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

        String date2= dateFormat.format(calendar.getTime());

        System.out.println(date2);


        }
    }

