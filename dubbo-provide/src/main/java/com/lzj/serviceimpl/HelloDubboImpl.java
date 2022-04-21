package com.lzj.serviceimpl;


import lzj.com.service.HelloDubbo;
import org.apache.dubbo.config.annotation.Service;


@Service
public class HelloDubboImpl implements HelloDubbo {
    @Override
    public String sayHello(String name){
        return "Hello,"+name;
    }
}
