package com.thinkinjava.strings;

import java.io.PrintStream;
import java.util.Formatter;

/**
 * Created by ning.wang on 2016/9/20.
 */
public class SimpleFormat {
    public static void main(String[] args) {
        int x =5;
        double y = 5.332542d;
        // The old way:
        System.out.println("Row 1: [" + x + " " + y + "]");
// The new way:
        System.out.format("Row 1: [%d %f]\n", x, y);
// or
        System.out.printf("Row 1: [%d %f]\n", x, y);
    }
}
class Turtle{
  private String name;
    private Formatter f;
    public Turtle(String name,Formatter f){
        this.name = name;
        this.f =f;
    }
    public void move(int x,int y){
        f.format("%s The Turtle is at (%d,%d)\n",name,x,y);
    }

    public static void main(String[] args) {
        PrintStream outAlias = System.out;
        Turtle tommy = new Turtle("Tommy", new Formatter(outAlias));
        Turtle tetty = new Turtle("Tetty", new Formatter(outAlias));
        tommy.move(0,0);
        tetty.move(4,8);
        tommy.move(3,4);
        tetty.move(2,5);
        tommy.move(3,3);
        tetty.move(3,3);
    }
}
