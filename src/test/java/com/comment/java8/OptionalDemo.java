package com.comment.java8;


import org.junit.Test;

import java.util.Optional;

/**
 * Created by ning.wang on 2016/3/31.
 */
public class OptionalDemo {
    @Test
    public void OptionalTest(){
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("fullName = " + fullName);
        System.out.println(fullName.isPresent());

        System.out.println(fullName.orElseGet(() -> "[none]"));

        System.out.println(fullName.map(s -> "hey "+s+"!").orElse(")-("));
        fullName.ifPresent(s -> System.out.println("s = " + s));

        fullName.ifPresent(System.out::println);
        //要使用新的视角来看待Optional 对null的处理
        System.out.println(fullName.map(s->s.toUpperCase()).orElse("---"));

        System.out.println(fullName.map(s->s.getBytes()).map(String::new).orElse("Nothing"));



    }
    @Test
    public  void test(){
        System.out.println("true = " + "7".matches("^\\d+$"));
    }
}
