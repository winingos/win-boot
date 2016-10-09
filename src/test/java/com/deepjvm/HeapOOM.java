package com.deepjvm;

import java.util.ArrayList;

/**
 * Created by ning.wang on 2016/9/28.<br/>
 *<em>cli<em/>  <br/><code>java -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError coo.deepjvm.HeapOOm</code><br/>
 *
 * ---------------output:----------------<br/>
 * java.lang.OutOfMemoryError: Java heap space
 * Dumping heap to java_pid13052.hprof ...
 * Heap dump file created [28085138 bytes in 0.099 secs]
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * ...
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
