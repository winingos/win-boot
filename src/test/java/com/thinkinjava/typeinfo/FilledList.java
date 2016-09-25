package com.thinkinjava.typeinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
class CountedInteger {
    private static long conter;
    private final long id = conter++;

    @Override
    public String toString() {
        return Long.toString(id);
    }
}

public class FilledList<T> {
    private Class<T> type;

    public FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int nEles) {
        List<T> ts = new ArrayList<T>();

        try {
            for (int i = 0; i < nEles; i++)
                ts.add(type.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return ts;

    }

    public static void main(String[] args) {
        FilledList<CountedInteger> fl = new FilledList<>(CountedInteger.class);
        System.out.println("fl = " + fl.create(15));
    }
}
