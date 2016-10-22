package com.thinkinjava.io;

import com.thinkinjava.io.print.PPrint;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by ning.wang on 2016/10/21.
 * Produce a sequence of File objects that match a
 * regular expression in either a local directory,
 * or by walking a directory tree
 */
public class Directory {
    public static File[] local(File dir,final String regex){
        return dir.listFiles((a,b)-> Pattern.matches(new File(b).getName(),regex));
    }

    public static File[] local(String path,final String regex){//Overloaded
        return local(new File(path),regex);
    }

    // A two-tuple for returning a pair of objects:
    public static class TreeInfo implements Iterable<File>{
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();
        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }
        void  addAll(TreeInfo other){
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }
        public String toString(){
            return "dirs: " + PPrint.pformat(dirs) +
                    "\n\nfiles: " + PPrint.pformat(files);
        }
    }
    public static TreeInfo walk(String start,String regex){//Begin recursion
        return recuresDirs(new File(start),regex);
    }

    public static TreeInfo walk(File start,String regex){//Overloaded
        return recuresDirs(start,regex);
    }
    public static TreeInfo walk(String start){//Everything
        return recuresDirs(new File(start),".*");
    }

    private static TreeInfo recuresDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {
            if (item.isDirectory()){
                result.dirs.add(item);
                result.addAll(recuresDirs(item,regex));
            }else //Regular file
                if (item.getName().matches(regex))
                    result.files.add(item);

        }
        return result;
    }
    //Simple validation test:
    public static void main(String[] args) {
        if (args.length==0)
            System.out.println("args = " + walk("."));
        else
            for (String arg : args) {
                System.out.println("walk(arg) = " + walk(arg));
            }
    }
    @Test
    public void testDirectory(){
        // All directories:
        PPrint.pprint(Directory.walk(".").dirs);
// All files beginning with ‘T’
        for(File file : Directory.local(".", "T.*"))
            System.out.println(file);
        System.out. println("----------------------");
// All Java files beginning with ‘T’:
        for(File file : Directory.walk(".", "T.*\\.java"))
            System.out.println(file);
        System.out.println("======================");
// Class files containing "Z" or "z":
        for(File file : Directory.walk(".",".*[Zz].*\\.class"))
            System.out.println(file);
    }
}
