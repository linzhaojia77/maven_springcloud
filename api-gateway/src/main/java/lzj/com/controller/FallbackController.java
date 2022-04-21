package lzj.com.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @RequestMapping("/fallback")
    public String fallback(){
        return "你所进行跳转路由后的路径访问出错，降级到这里来了";
    }
}