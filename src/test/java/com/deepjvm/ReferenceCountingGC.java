package com.deepjvm;

/**
 * Created by ning.wang on 2016/9/28.
 */
public class ReferenceCountingGC {
    private static final int _1MB = 1024 * 1024;
    public Object instance = null;
    /**
     * 这个成员属性唯一的意义就是站点内存,以便能在GC日志中看清楚是否被会收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    @org.junit.Test
    public  void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        //假设这行发生GC,objA和objB 是否能被回收
        System.gc();
    }
}
