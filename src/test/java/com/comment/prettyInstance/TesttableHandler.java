package com.comment.prettyInstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Mtime on 2016/2/4.
 * 模拟Junit框架
 */
public class TesttableHandler {
    public static void process(String clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int passed = 0;
        int failed = 0;
        Object obj = Class.forName(clazz).newInstance();
        for(Method method : Class.forName(clazz).getMethods()){
            if(method.isAnnotationPresent(Testable.class)){
                try {
                    method.invoke(obj);
                    ++passed;
                } catch (IllegalAccessException  | InvocationTargetException e) {
                    System.out.println("method " + method.getName() + " execute error: < " +e.getCause()+ " >");
                    e.printStackTrace(System.out);
                    ++failed;
//                    e.printStackTrace();
                }
            }
        }
        System.out.println("共运行" + (failed + passed) + "个方法, 成功" + passed + "个, 失败" + failed + "个");
    }
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
         TesttableHandler.process("com.comment.prettyInstance.TestCase");
    }
}
