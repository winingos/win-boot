package com.thinkinjava.concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by 王宁 on 2016/11/23.
 */
class TaskWithReuslt implements Callable<String>{
    private int id;
    public TaskWithReuslt(int id){
        this.id=id;
    }
    @Override
    public String call() throws Exception {
        return "result of TaskWihtResult "+ id;
    }
}
public class CallableDemo{
    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();
        ArrayList<Future<String>> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(exe.submit(new TaskWithReuslt(i)));
        }
        for (Future<String> fs : result) {
            //get() blocks until completion
            try {
                System.out.println("fs = " + fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exe.shutdown();
            }
        }
    }
}
