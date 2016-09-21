package com.thinkinjava.strings;

import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Created by ning.wang on 2016/9/21.
 */
public class ScannerDelimiter {
    public static void main(String[] args) {
        Scanner s = new Scanner("12, 42, 78, 99, 42");
        s.useDelimiter("\\s*,\\s*");
        while (s.hasNext())
            System.out.println("s = " + s.nextInt());
    }
}
class ThreatAnalyzer{
    static String threatData =
            "58.27.82.161@02/10/2005\n" +
                    "204.45.234.40@02/11/2005\n" +
                    "58.27.82.161@02/11/2005\n" +
                    "58.27.82.161@02/12/2005\n" +
                    "58.27.82.161@02/12/2005\n" +
                    "[Next log section with different data format]";

    public static void main(String[] args) {
        Scanner s = new Scanner(threatData);
        String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";
        while (s.hasNext(pattern)){
            s.next(pattern);
            MatchResult match = s.match();
            String ip = match.group(1);
            String date = match.group(2);
            System.out.format("Threat on %s from %s\n",date,ip);
        }
    }
}
