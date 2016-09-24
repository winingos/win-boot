package com.thinkinjava.holding;

import java.util.*;


/**
 * Created by ning.wang on 2016/9/13.
 */
public class SetOperations {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set,"A B C D E F G H I J K L".split(" "));
        set.add("M");
        System.out.println("H: " + set.contains("H"));
        System.out.println("N: " + set.contains("N"));
        HashSet<String> set1 = new HashSet<>();
        Collections.addAll(set1, "H I J K L".split(" "));
        System.out.println("set1 in set: " + set1.containsAll(set));
        set.remove("H");
        System.out.println("set: " + set);

        System.out.println("set1 in set: " + set.containsAll(set1));
        set1.removeAll(set);
        System.out.println("set1 removed from set: " + set1);
        Collections.addAll(set1, "X Y Z".split(" "));
        System.out.println("‘X Y Z’ added to set1: " + set1);

    }
}
class UniqueWordsAlphabetic{
    public static void main(String[] args) {
        Set<String> words = new TreeSet<>(/*String.CASE_INSENSITIVE_ORDER*/);
        Collections.addAll(words,("A, B, C, Collections, D, E, F, G, H, HashSet, I, J, K, L, M, N, Output," +
                "Print, Set, SetOperations, String, X, Y, Z, add, addAll, added, args," +
                "class, contains, containsAll, false, from, holding, import, in, java," +
                "main, mindview, net, new, print, public, remove, removeAll, removed," +
                "set1, set2, split, static, to, true, util, void").split(", "));
        System.out.println("words = " + words);
    }
}

/**
 * simple demonstration of hashMap.
 */

class Statistics{
    public static void main(String[] args) {
        Random rand = new Random(47);
        Map<Integer, Integer> m = new HashMap<>();
        for (int i=0;i<10000;i++){
            //produce a number between 0 and 20
            int r = rand.nextInt(20);
            Integer freq = m.get(r);
            m.put(r,freq==null?1:freq+1);
        }
        System.out.println("m = " + m);
    }
}
