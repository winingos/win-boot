package com.thinkinjava.generics;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/9/25 0025.
 */
public class ArrayMaker<T> {
    private Class<T> kind;
    public ArrayMaker(Class<T> kind){this.kind = kind;}
    @SuppressWarnings("unchecked")
    T[] create(int size){
        return (T[]) Array.newInstance(kind,size);
    }

    public static void main(String[] args) {
        ArrayMaker<String> strMaker = new ArrayMaker<>(String.class);
        String[] strings = strMaker.create(9);
        System.out.println("strings = " + Arrays.toString(strings));
    }
}
interface Payable<T>{}

class Employee implements Payable<Employee>{}

//class Hourly extends Employee implements Payable<Hourly>{}

class UseList<W,T>{
//    void f(List<T> v){}
    void f(List<W> v){}
}

class SelfBounded<T extends SelfBounded<T>>{}