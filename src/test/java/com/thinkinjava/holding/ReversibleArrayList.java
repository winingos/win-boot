package com.thinkinjava.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by ning.wang on 2016/9/13.
 */
public class ReversibleArrayList<T> extends ArrayList<T> {
    public ReversibleArrayList(Collection<T> c){super(c);}
    public Iterable<T> reversed(){
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int current = size() - 1;
                    @Override
                    public boolean hasNext() {
                        return current>-1;
                    }

                    @Override
                    public T next() {
                        return  get(current--);
                    }
                };
            }
        };
    }
    public Iterator<T> reversed1(){
        return new Iterator<T>() {
            int current = size()-1;
            @Override
            public boolean hasNext() {
                return current>-1;
            }

            @Override
            public T next() {
                return  get(current--);
            }
        };
    }

    public static void main(String[] args) {
        ReversibleArrayList<String> ral = new ReversibleArrayList<>(Arrays.asList("To be or not to be".split(" ")));
        //Grabs the ordinary iterator via iterator()
        for (String s : ral) {
            System.out.print("  " + s);
        }
        System.out.println();
        //Hand it the Iterable of your choice
        Iterable<String> reversed = ral.reversed();
        for (String s : reversed)
            System.out.print(" " + s);
        System.out.println();
        Iterator<String> stringIterator = ral.reversed1();
        for (String s : reversed)
            System.out.print(" " + s);
    }
}
