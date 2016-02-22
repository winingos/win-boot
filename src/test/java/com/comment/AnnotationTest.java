package com.comment;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mtime on 2016/2/4.
 */
public class AnnotationTest {
    @Override
    @MethodInfo(author = "Pankaj", comments = "Main method", priority = Priority.HIGH, revision = 1)
    public String toString() {
        return "Overriden toString method";
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", priority = Priority.MEDIUM)
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @MethodInfo(author = "Pankaj", comments = "Main method", revision = 1)
    public static void genericsTest() throws FileNotFoundException {
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }

}
