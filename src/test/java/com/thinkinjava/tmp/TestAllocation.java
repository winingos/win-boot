package com.thinkinjava.tmp;

/**
 * Created by Administrator on 2016/10/5 0005.
 */
public class TestAllocation {
    private static final int _1MB =1024*1024;
    /**
     * VM 参数:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *
     */

    /**
     * 新生代 MinorGC
     */
    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1=new byte[2*_1MB];
        allocation2=new byte[2*_1MB];
        allocation3=new byte[2*_1MB];
        allocation4=new byte[4*_1MB];//出现一次Minor GC
    }

    /**
     * 大对象直接进入老年代
     */
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[4*_1MB];//直接分配在老年代中
    }

    /**
     * 长期存活的对象进入老年代
     * -XX:MaxTenuringThreshold
     */
    public static void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3;
        allocation1 =new byte[_1MB/4];
        //什么时候进入老年代取决于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4*_1MB];
        allocation3 = new byte[4*_1MB];
        allocation3=null;
        allocation3=new byte[4*_1MB];
    }
    /**
     * 动态对象年龄判断
     * -XX:MaxTenuringThreshold=15
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold2(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB/4];
        //allocation1+allocattion2大于survivor空间的一半
        allocation2=new byte[_1MB/4];
        allocation3=new byte[4*_1MB];
        allocation4=new byte[4*_1MB];
        allocation4=null;
        allocation4=new byte[4*_1MB];
    }
    /**
     * 空间分配担保
     * -XX:-HandlePromotionFailure
     * JDK 6Update24 之前的版本测试
     */
    @SuppressWarnings("unused")
    public static void testHandlePromotion(){
        byte[] allocation1,allocation2,allocation3,allocation4
                ,allocation5,allocation6,allocation7;
        allocation1=new byte[2*_1MB];
        allocation2=new byte[2*_1MB];
        allocation3=new byte[2*_1MB];
        allocation1=null;
        allocation4=new byte[2*_1MB];
        allocation5=new byte[2*_1MB];
        allocation6=new byte[2*_1MB];
        allocation4=null;
        allocation5=null;
        allocation6=null;
        allocation7=new byte[2*_1MB];

    }
}
