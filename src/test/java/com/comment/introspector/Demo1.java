package com.comment.introspector;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ning.wang on 2016/5/10.
 * 一般的做法是通过类Introspector的getBeanInfo方法获取<br/>
 * 某个对象的BeanInfo 信息,然后通过BeanInfo来获取属性<br/>
 * 的描述器(PropertyDescriptor),通过这个属性描述器就可<br/>
 * 以获取某个属性对应的getter/setter方法,然后我们就可以通过反射机制来调用这些方法<br/>
 */
public class Demo1 {
    //使用BeanUtils工具包操作JavaBean
    @Test
    public void test() throws IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
        Demo.UserBean userInfo = (Demo.UserBean) Demo.UserBean.class.newInstance();
        //        String userName= BeanUtils.getProperty(userInfo, propertyName);
        //    System.out.println("userName="+userName);
        //    BeanUtils.setProperty(userInfo, propertyName, "linjiqin");
        //    userName=BeanUtils.getProperty(userInfo, propertyName);
        //    System.out.println("userName="+userName);
        setProperty(userInfo,"name","hello");
        System.out.println("userInfo = " + userInfo);
    }

    /**
     * 设置属性
     *
     * @param clazz        对象名
     * @param propertyName 属性名
     * @param value        属性值
     */
    private void setProperty(Object clazz, String propertyName, Object value)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
//方法一
//        PropertyDescriptor pd = new PropertyDescriptor(propertyName, clazz.getClass());
//        Method methodSet = pd.getWriteMethod();
//        methodSet.invoke(clazz, value);

//方法二
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (propertyName.equals(pd.getName())) {
                Method methodSet = pd.getWriteMethod();
                methodSet.invoke(clazz, value);
                break;
            }
        }
    }

    /**
     * 获取属性
     *
     * @param clazz        对象名
     * @param propertyName 属性名
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private Object getProperty(Object clazz, String propertyName)
            throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        //方法一
//        PropertyDescriptor pd = new PropertyDescriptor(propertyName, clazz.getClass());
//        Method methodGet = pd.getReadMethod();
//        return methodGet.invoke(clazz);

        //方法二
        Object retVal = null;
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (propertyName.equals(pd.getName())) {
                Method methodGet = pd.getReadMethod();
                retVal = methodGet.invoke(clazz);
                break;
            }
        }
        return retVal;
    }
}
