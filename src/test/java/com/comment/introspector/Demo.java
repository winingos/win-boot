package com.comment.introspector;

import lombok.Data;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ning.wang on 2016/5/10.
 */
public class Demo {
    @Data
    static public class UserBean{
        private String name;
        private int age;
    }

    private  void handleBean(UserBean user)
            throws IntrospectionException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
//        BeanInfo bi = Introspector.getBeanInfo(user.getClass());
//        PropertyDescriptor[] pd = bi.getPropertyDescriptors();
//        for (PropertyDescriptor p : pd) {
//            String attrName=p.getName();
//            if(attrName.equals("name")||attrName.equals("age")){
//                Method writeMethod=p.getWriteMethod();//得到set方法
//                Class clazz[]=writeMethod.getParameterTypes();
//                if(clazz[0]==int.class)
//                    writeMethod.invoke(user, 20);
//                else
//                    writeMethod.invoke(user, "peter");//执行set方法
//                Method readMethod = p.getReadMethod();//获取get方法
//                Object obj = readMethod.invoke(user);//执行get方法
//                System.out.println(obj);
//            }
//        }
        PropertyDescriptor pName = new PropertyDescriptor("name", user.getClass());
        pName.getWriteMethod().invoke(user,"peter");
        pName = new PropertyDescriptor("age",user.getClass());
        pName.getWriteMethod().invoke(user,33);
        System.out.println("user = " + user);

    }
    @Test
    public  void test() {
        UserBean user = new Demo.UserBean();
        try {
            handleBean(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        user.setAge(2);
//        System.out.println("user = " + user);
    }


}
