package com.thinkinjava.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 17/5/13.
 * 尽管你不能创建实际的持有泛型的数组对象,但是你可以创建非泛型的数组,然后将其转型
 */
public class ArrayOfGenerics {
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la=new List[10];
        ls=(List<String>[])la;//Unchecked warning
        ls[0]=new ArrayList<String>();
        //compile-time checking produce an error:
        //!ls[1]=new ArrayList<Integer>();
        //The problem List<String> is a subtype of Object
        Object[] objects=ls;//so assignment is OK
        //Compiles and runs without complaint:
        objects[1]=new ArrayList<Integer>();

        //
    }
}
