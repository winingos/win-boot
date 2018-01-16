package com.concurrency;

import java.awt.*;
import java.util.EventListener;

/**
 * Created by 王宁 on 2017/12/19.
 */
public class ThisEscape {

    public ThisEscape(EventSource source) {
        source.registerListaener(
                new EventListener() {
                    public void onEvent(Event e){
                      //doSomething(e);
                    }
                }
        );

    }

    ///

}
