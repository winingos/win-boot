package com.thinkinjava.innerclass.controller;

/**
 * Created by ning.wang on 2016/9/5.
 */
public abstract class Event {
    private long eventTime;
    protected final long delayTime;
    public Event(long delayTime){
        this.delayTime=delayTime;
        start();
    }
    public void start(){//Allows restarting
        eventTime= System.nanoTime()+delayTime;
    }
    public boolean ready(){
        return System.nanoTime()>=eventTime;
    }
    public abstract void action();
}
