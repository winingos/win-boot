package com.win.controller;

import com.win.controller.handler.DatePropertyEditor;
import com.win.controller.handler.MyException;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016-02-21.
 */

@EnableAutoConfiguration
@RequestMapping
@ComponentScan("com.win.controller.*")
@SpringBootApplication
public class HellowController {

    @Autowired
    ApplicationContext ctx;

    @RequestMapping("hello")
    @ResponseBody
    public String hellow(@RequestParam("test") int test) {

        Object bean = ctx.getBean("userMe");
        //throw new MyException("发生错误");
        return "哈喽，Spring Boot ！";
    }


    public static void main(String[] args) {
        //第一个简单的应用，
//        SpringApplication.run(HellowController.class, args);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-root.xml");
        context.getBean("userMe");
    }

}





