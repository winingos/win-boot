package com.deepjvm;

/**
 * Created by ning.wang on 2016/9/28.
 * VM Args: -Xss2M
 * 如果要运行,先要保存当前的工作
 * 由于windows平台的虚拟机中,Java的线程是映射到操作系统的内核上的,
 * 因此上述代码执行时有较大的风险,可能会导致操作系统假死
 */
public class JavaVMStackOOM {
    private void dontStop(){
        while (true){}
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(() -> dontStop());
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
