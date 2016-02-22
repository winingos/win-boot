package com.comment.prettyInstance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Mtime on 2016/2/4.
 */
public class TestCase {
    @Testable
    public void test1() {
        System.out.println("test1");
    }

    @TestableException(ArithmeticException.class)
    public void test2() throws IOException {
        System.out.println("test2");
        throw new IOException("我test2出错啦...");
    }

    @Testable
    public void test3() {
        System.out.println("test3");
        throw new RuntimeException("我test3出错啦...");
    }

    public void test4() {
        System.out.println("test4");
    }

    @Testable
    public void test5() {
        System.out.println("test5");
    }

    @Testable
    public void test6() {
        System.out.println("test6");
    }

    @TestableException(ArithmeticException.class)
    public void test7()throws IOException{
        int i=1/0;
        System.out.println(i);
    }
    @TestableException({ArithmeticException.class, IOException.class})
    public void test8() throws FileNotFoundException {
        FileInputStream stream = new FileInputStream("xxxx");
    }
}
