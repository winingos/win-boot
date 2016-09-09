package com.effective;

import com.google.common.collect.Lists;

import java.util.HashSet;

/**
 * Created by ning.wang on 2016/8/23.
 */
public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }
//    @Override
//    public boolean addAll(Collection<? extends E> c){
//        addCount += c.size();
//        return super.addAll(c);
//    }

    @Override
    public boolean add(E e){
        addCount ++;
        return super.add(e);
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedHashSet<String>  s = new InstrumentedHashSet<>();
        s.addAll(Lists.newArrayList("snap","carckle","pop"));
        System.out.println(" count= " + s.getAddCount());
    }
}
