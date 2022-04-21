package com.lzj.controller;

import com.lzj.service.FineName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/find")
public class findnamecontroller {
    @Autowired
    private FineName findname;
    @RequestMapping("/name")
    public String findname(){
        return findname.findname();
    }
}
