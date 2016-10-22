package com.deepjvm.jconsole;

/**
 * Created by ning.wang on 2016/10/9.
 * 线程死锁等待演示
 */
public class ThreadDeadLock {
    static class SynAddRunable implements Runnable{
        int a,b;
        public SynAddRunable(int a,int b){
            this.a=a;
            this.b=b;
        }
        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(b + a);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunable(1,2)).start();
            new Thread(new SynAddRunable(2,1)).start();
        }
    }
}
