package com.thinkinjava.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by ning.wang on 2016/10/21.
 * Display a directory listing using regular expressions
 * {Args:"D.*\.java"}
 */
public class DirList {
    public static void main(final String[] args) {
        File path = new File(".");//当前路径,跟期望的有出入
        System.out.println("path = " + path.getAbsolutePath());
//        args=new String[]{"D.*\\.class"};
        String[] list;
        final String[] name=args;

        if(args.length ==0)
            list = path.list();
        else
            list = path.list(new DirFilter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String s : list) {
            System.out.println("dirItem = " + s);
        }
    }
}
class DirFilter implements FilenameFilter{
    private Pattern pattern;
    public  DirFilter(String regex){
        pattern = Pattern.compile(regex);
    }
    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
