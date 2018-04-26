package com.win.jdbc;

import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Administrator on 18/2/10.
 */
public class SpringJDBCTest {
    public static void main(String[] args) {
        ApplicationContext cxt = new ClassPathXmlApplicationContext("spring/spr-jdbc.xml");
        UserService userService = (UserService) cxt.getBean("userService");
        User user = new User();
        user.setName("sss");
        user.setAge(23);
        user.setSex("female");
        userService.save(user);

        List<User> persons = userService.getUsers();
        System.out.println(JSON.toJSONString(persons));

    }
}
