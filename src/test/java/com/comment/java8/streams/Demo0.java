package com.comment.java8.streams;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ning.wang on 2016/2/15.
 */
public class Demo0 {
    List<String> stringCollection;

    @BeforeTest
    public void testStreams1() {
        stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        System.out.println("stringCollection = " + stringCollection);
    }

    @Test
//    Filter接受一个predicate接口类型的变量，并将所有流对象中的元素进行过滤
    public void testFilter() {
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
    }

    @Test
//    使用filter将colletiong中的元素改变类型
    public void testCovert() {
        Integer[] array = stringCollection
                .stream()
                .filter((s) -> s.length() > 3)
                .map((s) -> s = s.substring(3))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
    }

    @Test
//   Sorted是一个中间操作，能够返回一个排过序的流对象的视图
    public void testSorted() {
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        System.out.println("stringCollection = " + stringCollection);
    }

    @Test
//    map是一个对于流对象的中间操作，通过给定的方法，它能够把流对象中的每一个元素对应到另外一个对象上
    public void testMap() {
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);
    }

    @Test
//    匹配操作有多种不同的类型，都是用来判断某一种规则是否与流对象相互吻合的
    public void testMatch() {
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));
        System.out.println("anyStartsWithA = " + anyStartsWithA);

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));
        System.out.println("allStartsWithA = " + allStartsWithA);

        boolean noneStartWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));
        System.out.println("noneStartWithZ = " + noneStartWithZ);
    }

    @Test
//    Count是一个终结操作，它的作用是返回一个数值，用来标识当前流对象中包含的元素数量
    public void testCount() {
        long startWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        System.out.println("startWithB = " + startWithB);
    }

    @Test
//    该操作是一个终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的结果会放在一个Optional变量里返回。
    public void testReduce() {
        stringCollection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s2 + "#" + s1)
                .ifPresent(System.out::println);
    }

}
