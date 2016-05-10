package com.comment.reflect;

import lombok.Data;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * Created by Mtime on 2016/2/5.
 */
@Data
public  class  TestReflection {


    private  String username;

    private String password;

    private int[] age;


//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    private void setPassword(String password) {
//        this.password = password;
//    }
//    @Override
//    public String toString(){
//        return "username="+username+",password="+password;
//    }


    public static void main(String[] args) {
        Class clazz = TestReflection.class;
//        获取包

        System.out.println("pacakage = [" + clazz.getPackage().getName() + "]");
//        获取类的修饰符
        int modifiers = clazz.getModifiers();
        String modifier = Modifier.toString(modifiers);
        System.out.println("modifier = " + modifier);
        //获取指定类的完全限定名
        String name = clazz.getName();
        System.out.println("name = " + name);
        //获取指定类的父类
        String superName = clazz.getSuperclass().getName();
        System.out.println("superName = " + superName);
        
        //获取实现的接口
        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println("anInterface = " + anInterface);
        }
        
        //获取指定类的成员变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            modifier = Modifier.toString(field.getModifiers());//获取每个的访问修饰符

//获取字段的数据类型所对应的class对象
            Class<?> type = field.getType();
            String s = field.getName();//获取字段名
            if (type.isArray()) { //如果是数组类型则需要特别处理
                String arrType = type.getComponentType().getName();
                System.out.println("" + modifier + " " + arrType + "[]   " + s + ";");
            } else {
                System.out.println("" + modifier + " " + type + " " + s + ";");
            }
        }
        //获取类的构造方法
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String name1 = constructor.getName();//构造方法名
            String mod = Modifier.toString(constructor.getModifiers()); //获取访问修饰符
            System.out.print("" + mod +" " + name1 + "(");
            //获取构造方法中的参数
            Class[] types = constructor.getParameterTypes();
            for (int i = 0; i < types.length; i++) {
                if (i>0){ System.out.println(",");}
               paramTypes(types[i]);
            }
            System.out.println(");");

        }
        //获取成员方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            modifier = Modifier.toString(method.getModifiers());
            Class<?> returnType = method.getReturnType();//获取方法的返回类型
            if(returnType.isArray()){
                String s = returnType.getComponentType().getName() + "[]";
                System.out.print(modifier+"   "+s+"  "+method.getName()+"(");
            }else {
                System.out.print("" + modifier + " " +returnType.getName() + "  " + method.getName() + "(");
            }
            Class<?>[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {   System.out.print(","); }
                paramTypes(paramTypes[i]);
            }
            System.out.println(");");

        }
    }

    //获取type
    private static void paramTypes(Class<?> paramType) {
        if (paramType.isArray()) {
            System.out.print(paramType.getComponentType().getName()+"[]");
            } else {
            System.out.print(paramType.getName());
            }
    }
    //反射调用方法，可以通过Method类的invoke方法实现动态方法的调用
    //public Object invoke(Object obj, Object... args)
    //第一个参数代表对象
    //第二个参数代表执行方法上的参数
    //若反射要调用类的某个私有方法，可以在这个私有方法对应的Mehtod对象上先 调用setAccessible(true)
    @Test
    public void test33() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class c1 = TestReflection.class;
        TestReflection t1 = (TestReflection) c1.newInstance(); //利用反射来创建类的对象
        System.out.println(t1.toString());
     //t1.setUsername();
        Method method = c1.getDeclaredMethod("setUsername", String.class);
        method.invoke(t1, "learn java reflect!");
        System.out.println(t1.toString());
        Method method1 = c1.getDeclaredMethod("setPassword",String.class);
        method1.setAccessible(true);
        method1.invoke(t1,"反射执行某个Private修饰的方法");
        System.out.println(t1.toString());
    }
}
