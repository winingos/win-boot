package com.thinkinjava.array;

/**
 * Created by Administrator on 17/5/13.
 * 参数化数组本身的类型
 */
public class ParameterizedArrayType {
    public static void main(String[] args) {
        Integer[] ints={1,2,3,4,5};
        Double[] doubles={1.1,2.2,3.3,4.4,5.5};
        Integer[] ints2=new ClassParameter<Integer>().f(ints);
        Double[] doubles2=new ClassParameter<Double>().f(doubles);
        ints2=MethodParamter.f(ints);
        doubles2=MethodParamter.f(doubles);
    }
}

class ClassParameter<T>{
    public T[] f(T[] arg){return arg;}
}

class MethodParamter{
    public static <T> T[] f(T[] arg){return arg;}
}