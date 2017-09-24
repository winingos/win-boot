package com.win.controller.handler;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 17/9/15.
 */
class TestEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public String msg;
    public TestEvent(Object source) {
        super(source);
    }
    public TestEvent(Object source,String msg){
        super(source);
        this.msg=msg;
    }
    public void print(){
        System.out.println(msg);
    }
}
public class TestListener implements ApplicationListener{

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof TestEvent){
            ((TestEvent)event).print();
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:appContext.xml");
        TestEvent event = new TestEvent("hello", "msg");
        context.publishEvent(event);
    }
}
