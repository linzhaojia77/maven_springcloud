package com.lzj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/find")
public class FindName {
    @RequestMapping("/name")
    public String findname(){
        System.out.println("我被调用了");
        return "我的名字是小佳";
    }
    @RequestMapping("/gateway")
    public String gateway(){
        return "被路由匹配转换后来到了这里";
    }
}
