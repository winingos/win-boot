package com.win.aop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 17/9/16.
 */
@Getter
@Setter
@Component
public class TestBean {
    private String testStr = "testStr";

    public void test() {
        System.out.println("test");
    }
    public static void main(String[] args) {

        ApplicationContext bf = new ClassPathXmlApplicationContext("appContext.xml");
        TestBean bean = (TestBean) bf.getBean("testBean");
        bean.test();

    }

}
