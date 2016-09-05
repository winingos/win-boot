package com.thinkinjava.innerclass.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ning.wang on 2016/9/5.
 * The reusable framework for control systems.
 */
public class Controller {
    // A class from java.util to hold Event objects:
    private List<Event> eventList = new ArrayList<>();
    public void addEvent(Event c){
        eventList.add(c);
    }
    public void run(){
        while (!eventList.isEmpty())
// Make a copy so you’re not modifying the list
// while you’re selecting the elements in it:
        for (Event e :new ArrayList<>(eventList))
            if (e.ready()){
                System.out.println("e = " + e);
                e.action();
                eventList.remove(e);
            }
    }
}
