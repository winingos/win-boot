package com.concurrency;

import java.awt.*;
import java.util.EventListener;

/**
 * Created by 王宁 on 2017/12/19.
 */
public class SafeListener {
    private final EventListener listener;
    public SafeListener(){
        listener=new EventListener() {
            public void onEvent(Event e){
                //doSomething(e);
            }
        };
    }
    public static SafeListener newInstance(EventSource source){
        SafeListener safe = new SafeListener();
        source.registerListaener(safe.listener);
        return safe;
    }
}
