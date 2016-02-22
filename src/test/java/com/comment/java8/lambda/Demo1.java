package com.comment.java8.lambda;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ning.wang on 2016/2/15.
 */
@FunctionalInterface
interface Converter<F,T>{
    T convert (F from);
}
public class Demo1 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
//        System.out.println("names = " + names);
//        Collections.sort(names,(String a,String b) ->{
//            return b.compareTo(a);
//        });

        Collections.sort(names,(a,b)->b.compareTo(a));
        System.out.println("names = " + names);
        Converter<String ,Integer> converter1 = new Converter<String, Integer>() {
            @Override
            public Integer convert(String from) {
                return Integer.valueOf(from);
            }
        };
        Converter<String ,Integer> converter = (from) -> Integer.valueOf(from);

        Converter<String,Integer> converter2 = Integer::valueOf;

        System.out.println(converter1.convert("2345"));
    }
    class Something{
        String startsWith(String s){
            return String.valueOf(s.charAt(0));
        }
    }
    @Test
    public void lambdaTest1(){
        Something something = new Something();
        Converter<String,String> converter = something::startsWith;
        System.out.println("converter = " + converter.convert("Java"));
    }
//使用::关键字引用构造函数
class Person {
    String firstName;
    String lastName;

    Person() {
    }

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Override
    public String toString(){
        return firstName+"0"+lastName;
    }
}

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }
    @Test
    public void lambdaTest2(){
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter","Parker");
        System.out.println("person = " + person);
    }


}

//在lambda表达式的内部能获取到对成员变量或静态变量的读写权
class Lambda {
    static int outerStaticNum;
    int outerNum;

    void testScopes() {
      Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

}
