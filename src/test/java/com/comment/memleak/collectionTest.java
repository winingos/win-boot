package com.comment.memleak;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Timer;
import com.yammer.metrics.core.TimerContext;
import com.yammer.metrics.reporting.ConsoleReporter;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by 王宁 on 2017/5/31.
 */
public class collectionTest {
    private static Timer timer = Metrics.newTimer(collectionTest.class, "responses", TimeUnit.MILLISECONDS,TimeUnit.SECONDS);
    public static void main(String[] args) throws Exception {

//        AbstractList vector = new LinkedList();
//        for (int i = 0; i<100; i++)
//        {
//            Object object = new Object();
//            vector.add(object);
//            object = null;
////            vector.set(i,null);
//        }
//        System.out.println("vector = " + JSON.toJSONString(vector));

        // TODOAuto-generated method stub

        ConsoleReporter.enable(2, TimeUnit.SECONDS);
        Random rn = new Random(47);
        timer.time();
        System.out.println();
        while(true){
            TimerContext context = timer.time();

            Thread.sleep(rn.nextInt(1000));
            context.stop();
        }
    }
}

