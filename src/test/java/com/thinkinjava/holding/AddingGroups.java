package com.thinkinjava.holding;

import java.util.*;

/**
 * Created by ning.wang on 2016/9/5.
 * Adding groups of elements to Collection objects.
 */
public class AddingGroups {
    public static void main(String[] args) {
        Collection<Integer> col = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] moreInts={6,7,8,9,10};
        col.addAll(Arrays.asList(moreInts));
//        Runs significantly faster,but you can't
//        construct a Collection this way:
        Collections.addAll(col,11,12,13,14,15);
        Collections.addAll(col,moreInts);
//        Produces a list "backed by" an array:
        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
        list.set(1,99);//ok modify an element
        //list.add(21);//Runtime error because the underlying array cannot be resized;
        System.out.println("list = " + list);

        Map<Integer, String> m = /*new LinkedHashMap<>();*/new HashMap<>();
        m.put(1,"2");
        m.put(2,"2");
        m.put(4,"2");
        m.put(3,"2");
        System.out.println("m = " + m);

    }
}
