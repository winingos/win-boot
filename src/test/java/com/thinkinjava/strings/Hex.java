package com.thinkinjava.strings;

/**
 * Created by ning.wang on 2016/9/20.
 * look at the bytes inside a binary file using hex format
 */
public class Hex {
    public static String format(byte[] data){
        StringBuilder result = new StringBuilder();
        int n=0;
        for (byte b : data) {
            if(n%16==0)
                result.append(String.format("%05X: ",n));
            result.append(String.format("%02X ",b));
            n++;
            if(n%16==0) result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }

    public static void main(String[] args) {
        if(args.length == 0)
// Test by displaying this class file:
            System.out.println(
                    /*format(BinaryFile.read("Hex.class"))*/);
        else
            System.out.println(
                    /*format(BinaryFile.read(new File(args[0])))*/);
    }
}
