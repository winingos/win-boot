package com.comment;

import java.util.Arrays;

/**
 * Created by Administrator on 17/5/1.
 * Char： 在java中是采用UTF-16编码的，也就是说，Char是代表一个字符单元。
 * 代码单元：UTF-8中是用8个字节表示的，UTF-16中使用16个字节表示的等等。
 * 代码点：对应各种真正字符（char不是真正的字符，是代码单元）的Unicode编码
 */
public class CodePoint {
    public static void main(String[] args) {
        String sentence = "\u03C0 \uD835\uDD6B";
        int lengthU = sentence.length(); //返回代码单元的数量
        int lengthP = sentence.codePointCount(0, lengthU);//0-length单元之间，返回代码点的数量
        System.out.println(lengthU);        // 4个code units
        System.out.println(lengthP);         // 3个code points

        int index = sentence.offsetByCodePoints(0, 1); //返回第i个代码点的专用索引
        int i1 = sentence.codePointAt(index);//返回第i个代码点
        System.out.println("i1 = " + (char) i1);

        int i = sentence.codePointAt(2);    // i=2 true  i=0,1,3 false  i=4 out of bound
        boolean b = Character.isSupplementaryCodePoint(i);
        System.out.println(b);


        for (int j = 0; j < sentence.length(); ) {
            int cp = sentence.codePointAt(j);
            if (Character.isSupplementaryCodePoint(cp)) {
                j += 2;  //判断是否是辅助字符
                System.out.println("cp = " + cp);
            } else {
                System.out.println("cp = " + cp);
                j++;
            }
        }

        System.out.println(Arrays.toString(" ".getBytes()));
    }
}
