package com.lzj.serviceimpl;

import com.lzj.service.FineName;
import org.springframework.stereotype.Component;

@Component
public class FineNameImpl implements FineName {
    @Override
    public String findname(){
        return "服务调用失败，降级来到了这里";
    }
}
