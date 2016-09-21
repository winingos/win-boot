package com.thinkinjava.strings;

import com.thinkinjava.exceptions.InputFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ning.wang on 2016/9/21.
 * A very simple version of the "grep" program
 * {Args: JGrep.java "\b[Ssct]\w+"}
 */
public class JGrep {
    public static void main (String[] args)throws Exception {
        if(args.length < 2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        //Iterate throuht the line of the input file
        int index = 0;
        Matcher m = p.matcher(" ss");
        InputFile in = new InputFile(args[0]);
        String s;
        while((s=in.getLine())!=null){
            //System.out.println("s = " + s);
            m.reset(s);
            while (m.find()) {
                System.out.println(index++ + ": " +
                        m.group() + ": " + m.start());
            }
        }
    }
}
