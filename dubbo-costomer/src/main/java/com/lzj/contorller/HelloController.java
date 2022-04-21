package com.lzj.contorller;




import lzj.com.service.HelloDubbo;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Reference
    private HelloDubbo helloDubbo;
    @RequestMapping("/sayhello")
    private String sayhello(@RequestParam String name){
        System.out.println("调用成功");
        return helloDubbo.sayHello(name);
    }
}
