package com.thinkinjava.io;

import java.io.*;

/**
 * Created by Mtime on 2016/10/31.
 */
public class BinaryFile {
    public static byte[] read(File bFile)throws IOException{
        BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile));
        try {
            byte[] data = new byte[bf.available()];
            bf.read(data);
            return data;
        } finally {
            bf.close();
        }
    }
    public static byte[] read(String bFile)throws IOException{
        return read(new File(bFile).getAbsoluteFile());
    }
}
//How to read from standard input
class Echo{
    public static void main(String[] args)throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=stdin.readLine())!=null&&s.length()!=0){
            System.out.println(s);
        }
        //An empty line or ctrl-z terminates the program
    }
}

class ChangeSystemOut{
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello ,world");
    }
}
//Demonstrates standard I/O redirection
class Redirecting{
    public static void main(String[] args) throws IOException{
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("D:\\IdeaWorkSpaec\\win-boot\\src\\test\\java\\com\\thinkinjava\\io\\BinaryFile.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=br.readLine())!=null){
            System.out.println(s);
        }
        out.close();//Remember this!
        System.setOut(console);
    }
}
