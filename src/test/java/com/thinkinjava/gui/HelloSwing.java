package com.thinkinjava.gui;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 17/4/23.
 */
public class HelloSwing {
    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame("hello swing");
        JLabel l=new JLabel("A lable");
        f.add(l);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300,100);
        f.setVisible(true);
        TimeUnit.SECONDS.sleep(1);
        l.setText("Hey! this is Different!");
    }
}
