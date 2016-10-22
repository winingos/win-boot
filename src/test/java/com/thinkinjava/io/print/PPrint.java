package com.thinkinjava.io.print;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by ning.wang on 2016/10/21.
 * Pretty-printer for collections
 */
public class PPrint {
    public static String pformat(Collection<?> c){
        if (c.size()==0)return "[]";
        StringBuilder stv = new StringBuilder("[");
        for (Object elem : c) {
            if (c.size()!=1)
                stv.append("\n  ");
            stv.append(elem);
        }
        if (c.size()!=1)
            stv.append("\n");
        stv.append("]");
        return stv.toString();
    }
    public static void pprint(Collection<?> c){
        System.out.println(pformat(c));
    }
    public static void pprint(Object[] c){
        pprint(Arrays.asList(c));
    }
}
