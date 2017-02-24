package com.comment.java8.lambda;


import org.junit.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by ning.wang on 2016/2/15.
 */
public class InnerFunc {
    @Test
    //Predicates 一个布尔类型的函数
    public void test0(){
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        System.out.println("isNull.test(null) = " + isNull.negate().test(null));
        System.out.println("nonNull = " + nonNull.test(null));
//        Objects.requireNonNull()

        Predicate<String> isEmpty = String::isEmpty;
        
        Predicate<String> isNotEmpty = isEmpty.negate();
    }
    @Test
    //Function接口接收一个参数，并返回单一的结果。默认方法可以将多个函数串在一起（compse, andThen）
    public void test1(){
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
    }

    @Test
    //Optional不是一个函数式接口，而是一个精巧的工具接口，用来防止NullPointerEception产生
    public void test2(){
        Optional<String> optional = Optional.ofNullable("bam");
//        optional.isPresent();           // true
//        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));
    }
}
