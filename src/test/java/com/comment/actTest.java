package com.comment;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Mtime on 2016/2/4.
 */
public class actTest {
    public static void main(String[] args) throws ClassNotFoundException {
        for(Method method: Class.forName("com.comment.AnnotationTest").getMethods()){
            // checks if MethodInfo annotation is present for the method
            if(method.isAnnotationPresent(com.comment.MethodInfo.class)){   //判断方法是否加此注释
                for(Annotation an :method.getAnnotations()){
                    System.out.println("Annotation in Method-" + method + ":"+ an);
                    if(an instanceof MethodInfo){             //判断对象是否可以强转为相应类型
                    System.out.println("comments = [" + ((MethodInfo)an).comments() + "]");}
                }
                MethodInfo info = method.getAnnotation(MethodInfo.class);
                if(info.revision()==1 ){
                    System.out.println("Method with revision no 1 = " + method.getName() );
                    System.out.println("Method with revision no 1 = " + info.annotationType().getName() );
                }

            }
        }
    }
}
