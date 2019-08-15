package com.rick.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    Logger logger =LoggerFactory.getLogger("HelloController");

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        logger.info("此时进入了HelloController中的ssuccess方法");
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("张三","<h2>李四</h2>","王五"));
        return "success";
    }
}

