package com.thinkinjava.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ning.wang on 2016/9/20.
 * Allows you to easily try out regular expressions
 * {Args: abcabcabcdefabc "abc+" "(abc)+" "(abc){2,}
 */
public class TestRegularExpression {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.print("Usage:\njava TestRegularExpression " +
                    "characterSequence regularExpression+");
            System.exit(0);
        }
        System.out.println("Input: \"" + args[0] + "\"");
        for (String arg : args) {
            System.out.println("Regular expression: \"" + arg + "\"");
            Pattern p = Pattern.compile(arg);
            Matcher m = p.matcher(args[0]);
            while (m.find()) {
                System.out.println("Match \"" + m.group() + "\" at positions " +
                        m.start() + "-" + (m.end() - 1));
            }
        }
    }
}
class Finding{
    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("\\w+").matcher("Evening is full of the linnet’s wings");
        while(matcher.find())
            System.out.print(matcher.group() +"   ");
        System.out.println();
        int i=0;
        while(matcher.find(i)){
            System.out.print(matcher.group()+"  ");
            i++;
        }
    }
}

class Groups{
    static public final String POEM =
            "Twas brillig, and the slithy toves\n" +
                    "Did gyre and gimble in the wabe.\n" +
                    "All mimsy were the borogoves,\n" +
                    "And the mome raths outgrabe.\n\n" +
                    "Beware the Jabberwock, my son,\n" +
                    "The jaws that bite, the claws that catch.\n" +
                    "Beware the Jubjub bird, and shun\n" +
                    "The frumious Bandersnatch.";

    public static void main(String[] args) {
        Matcher m = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$")
                .matcher(POEM);
        while(m.find()){
            for (int j=0;j<=m.groupCount();j++)
                System.out.print(" [" + m.group(j) +" ]");
            System.out.println();
        }

//      count all of unique words that do not start with a capital letter
        int count=0;
        Matcher m1 = Pattern.compile("\\s[a-z]\\w+").matcher(POEM);
        while (m1.find()){
            count ++;
            for (int j=0;j<=m1.groupCount();j++)
                System.out.print(" [" + m1.group(j) +" ]");
            System.out.println();
        }
        System.out.println("the summary of without capital letter is "+count);
    }
}
class Resetting{
    public static void main(String[] args) {
        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while (m.find()){
            System.out.println("args = [" + m.group() + "]");
        }
        m.reset("fix the rig with rags");
        while (m.find())
            System.out.println(m.group() +"  ");
    }
}
