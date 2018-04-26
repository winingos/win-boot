package com.concurrency;

import javax.annotation.processing.Completion;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.FutureTask;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by 王宁 on 2018/4/26.
 * timer 缺点: 单线程,  长时间的任务执行时,中间有短时间的任务将不会执行
 *             2.未受检异常将导致,线程直接退出
 *             3.基于绝对时间,对时钟变化很敏感
 * 本示例说明了:你可能以为程序会运行6s退出,实际上只运行了1s
 *
 */
public class OutOfTime {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(5);
    }
    static class ThrowTask extends TimerTask {
        public void run() { throw new RuntimeException(); }
    }
}
