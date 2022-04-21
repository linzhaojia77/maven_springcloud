package com.lzj.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.naming.Name;
import java.util.List;
import java.util.concurrent.Future;


@RestController
@RequestMapping("/hystrix")
public class FindUserController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.user-service}")
    private String userServiceUrl;
    @RequestMapping("/findname")
    @HystrixCommand(fallbackMethod = "getDefaultName",commandKey = "findname")
    //通过一个String来对方法进行缓存处理，即一次连接中如果调用多次这个方法，只有第一次会走这个方法，其余的走的是缓存
    @CacheResult(cacheKeyMethod = "getCacheKey")
    public String findname(){
        return restTemplate.getForObject(userServiceUrl+ "/find/name", String.class);
    }
    @HystrixCollapser(batchMethod = "getCollapser",collapserProperties = {@HystrixProperty(
            name="timerDelayInMilliseconds",value = "100")})
    public Future<String> testCollapser(Long name){
        System.out.println("如果没被合并，那我叫"+name);
        return new AsyncResult<String>("如果没被合并，那我叫"+ name);
        }
    public String getDefaultName(){
        return "我是服务调用被熔断之后的名：默认名";
    }
    public String getCacheKey(){
        return "1587";
    }
    @HystrixCommand
    public String getCollapser(List<Long> listString){
        Long myname = 0L;
        for(Long name:listString){
            myname = myname+name;
        }
        System.out.println("我是被合并的名字"+myname);
        return "我是被合并的名字"+myname;}
}
