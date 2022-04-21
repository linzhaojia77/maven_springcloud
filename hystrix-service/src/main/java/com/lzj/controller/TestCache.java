package com.lzj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class TestCache {
    @Autowired
    private FindUserController findUserController;

    @RequestMapping("/testcache")
    public String testcache() {
        findUserController.findname();
        findUserController.findname();
        return findUserController.findname();
    }

    @RequestMapping("/testCollapser")
    public String testcollapser() throws ExecutionException, InterruptedException {
        Future<String> future1 = findUserController.testCollapser(1L);
        Future<String> future2 = findUserController.testCollapser(2L);
        future1.get();
        future2.get();
        Thread.sleep(200);
        Future<String> future3 = findUserController.testCollapser(5L);
        future3.get();
        return "测试成功";
    }
}