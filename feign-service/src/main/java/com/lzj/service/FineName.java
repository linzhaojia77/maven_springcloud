package com.lzj.service;

import com.lzj.serviceimpl.FineNameImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "eureka-client",fallback = FineNameImpl.class)
public interface FineName {
    @RequestMapping("/find/name")
    String findname();
}
