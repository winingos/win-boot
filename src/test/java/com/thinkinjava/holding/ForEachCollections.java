package com.thinkinjava.holding;

import java.util.*;

/**
 * Created by ning.wang on 2016/9/13.
 * All collections work with foreach.
 */
public class ForEachCollections {
    public static void main(String[] args) {
        Collection<String> cs = new LinkedList<>();
        Collections.addAll(cs,"Take the long way home".split(" "));
        for (String c : cs) {
            System.out.println("c = " + c);
        }
    }
}
class IterableClass implements Iterable<String>{
    protected String[] words = ("And that is how " +
            "we know the Earth to be banana-shaped.").split(" ");
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index=0;
            @Override
            public boolean hasNext() {
                return index<words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }
        };
    }

    public static void main(String[] args) {
        for (String s : new IterableClass()) {
            System.out.println("s = " + s);
        }
        Set<Map.Entry<String, String>> entries = System.getenv().entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+" = " + entry.getValue());
        }

    }
}
