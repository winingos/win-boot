package com.thinkinjava.innerclass;

/**
 * Created by ning.wang on 2016/9/1.
 * Creating a constructor for an anonymous inner class.
 */
abstract class Base{
    public Base(int i){
        System.out.println("Base constructor,i = " + i);
    }
    public abstract void f();
}
public class AnonymousConstructor {
    public static Base getBase(int i){
        return new Base(i) {
            {
                System.out.println("Inside instance initializer");
            }
            @Override
            public void f() {
                System.out.println("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
}
