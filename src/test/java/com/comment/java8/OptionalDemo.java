package com.comment.java8;

import org.testng.annotations.Test;

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
    }
}
