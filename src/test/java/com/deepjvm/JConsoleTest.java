package com.deepjvm;

import java.util.ArrayList;

/**
 * Created by ning.wang on 2016/10/9.
 * JConsole 监视代码
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class JConsoleTest {
    static class OOMObject{
        public byte[] placeholder = new byte[64*1024];
    }
    public static void fillHeap(int num)throws InterruptedException{
        ArrayList<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
//            稍作延时,令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args)throws Exception {
        fillHeap(1000);
    }
}
