package com.thinkinjava.generics;

/**
 * Created by Administrator on 2016/9/25 0025.
 */
public class GenericHolder<T> {
    private T obj;
    public void set(T obj) { this.obj = obj; }
    public T get() { return obj; }

    public static void main(String[] args) {
        GenericHolder<String> holder =
                new GenericHolder<String>();
        holder.set("Item");
        String s = holder.get(); // Why it works?
        System.out.println("s = " + s);
    }
}
