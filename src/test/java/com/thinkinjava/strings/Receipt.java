package com.thinkinjava.strings;

import java.util.Formatter;

/**
 * Created by ning.wang on 2016/9/20.
 */
public class Receipt {
    private double total = 0;
    private String format;
    private Formatter f =new Formatter(System.out);
    private String getFormat(String s,String s1,String s2){
        StringBuilder stb = new StringBuilder();
        return stb.append("%-").append(s).append(" %").append(s1).append(" %")
                .append(s2).append("\n").toString();
    }
    public void setFormate(String s,int i,double j){
        format=getFormat(s+"s",i+"d",j+"f");
    }
    public void printTitle(){
        f.format("%-15s %5s %10s\n", "Item", "Qty", "Price");
        f.format("%-15s %5s %10s\n", "----", "---", "-----");
    }
    public void print(String name,int qty,double price){
        f.format(/*"%-15.15s %5d %10.2f\n"*/format, name, qty, price);
        total += price;
    }
    public void printTotal(){
        f.format("%-15s %5s %10.2f\n", "Tax", "", total*0.06);
        f.format("%-15s %5s %10s\n", "", "", "-----");
        f.format("%-15s %5s %10.2f\n", "Total", "",
                total * 1.06);
    }

    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.setFormate("15.15",5,10.2D);
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess Peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }
}
