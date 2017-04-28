package com.win.controller;

import com.win.controller.handler.MyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016-02-21.
 */

@EnableAutoConfiguration
@RequestMapping
@ComponentScan("com.win.controller.*")
public class HellowController {
    @RequestMapping("hello")
    @ResponseBody
    public String hellow(@RequestParam("test")int test){
        throw new MyException("发生错误");
//        return "哈喽，Spring Boot ！";
    }
    public static void main(String[] args) {
        //第一个简单的应用，
        SpringApplication.run(HellowController.class,args);
    }
}
