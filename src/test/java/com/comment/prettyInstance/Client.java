package com.comment.prettyInstance;

import org.testng.annotations.Test;

import java.lang.annotation.Annotation;

/**
 * Created by Mtime on 2016/2/4.
 */
@Tag
public class Client {

    @Test
    public void cinent(){
        new subClass();
    }

    @Test
//    获取annotation 内容
    public void client1() throws NoSuchMethodException {
        for (Annotation annotation : this.getClass().getAnnotations()) {
            if (annotation instanceof Tag) {
                Tag tag = (Tag) annotation;
                System.out.println("name: " + tag.name());
                System.out.println("description: " + tag.description());
            }
        }
    }
}
@Testable
class SuperClass{}
class subClass extends SuperClass{
    public subClass(){
        for(Annotation ann: subClass.class.getAnnotations()){
            System.out.println("annotation:"+ann);
        }
    }
}