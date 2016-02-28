package com.win.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016-02-21.
 */

@EnableAutoConfiguration
@RequestMapping
public class HellowController {
    @RequestMapping("hello")
    @ResponseBody
    public String hellow(){
        return "哈喽，Spring Boot ！";
    }
    public static void main(String[] args) {
        //第一个简单的应用，
        SpringApplication.run(HellowController.class,args);
    }
}
