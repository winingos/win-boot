package com.win.common.dto.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类
 * Created by Sun Wang on 2016/1/6.
 */
public class ReflectUtil {

   // private static final Logger logger = LoggerManager.getLogger(ReflectUtil.class);

    /**
     * 获取方法，不存在时返回null
     * @param clazz
     * @param methodName
     * @param paramTypes
     * @return
     */
    public static Method getMethod(Class clazz, String methodName, Class...paramTypes) {
        try {
            return clazz.getDeclaredMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            //logger.warn("没有反射到{}方法", methodName);
        }
        return null;
    }

    /**
     * 执行方法，调用失败抛出异常
     * @param method
     * @param instance
     * @param params
     * @return
     */
    public static Object invoke(Method method, Object instance, Object...params) {
        try {
            return method.invoke(instance, params);
        } catch (Exception e) {
            //logger.error("方法调用异常", e);
            throw new RuntimeException("方法调用异常", e);
        }
    }

    /**
     * 获取属性-属性值映射
     * @param obj
     * @return
     */
    public static Map<Field, Object> getFieldValues(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<Field, Object> mapper = new HashMap<>();
        for(Field field : fields) {
            int m = field.getModifiers();
            if(Modifier.isStatic(m) || Modifier.isFinal(m)) continue;
            try {
                field.setAccessible(true);
                mapper.put(field, field.get(obj));
            } catch (IllegalAccessException e) {
                //logger.error("获取{}对象{}属性值失败", obj.getClass().getName(), field.getName(), e);
            }
        }
        return mapper;
    }

    /**
     * 实例化
     * @param clazz
     * @param throwError
     * @param <T>
     * @return
     */
    public static <T> T getInstance(Class<T> clazz, boolean throwError) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            if(throwError) {
                //throw new FaultException("{}类型实例化异常", clazz.getName(), e);
            } else {
                //logger.warn("{}类型实例化失败", clazz.getName(), e);
            }
        }
        return null;
    }

    /**
     * 获取字段声明的泛型类
     * @param field
     * @return
     */
    public static Class getGenericType(Field field) {
        try {
            Class type = field.getType();
            if(Collection.class.isAssignableFrom(type)){
                return (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            } else if(Object[].class.isAssignableFrom(type)) {
                String typeName = type.getTypeName();
                return Class.forName(typeName.substring(0, typeName.length() - 2));
            }
        } catch (Exception e) {
            //logger.warn("获取{}字段的泛型类失败", field.getName(), e);
        }
        return null;
    }

    /**
     * 设置对象指定字段值
     * @param model
     * @param field
     * @param fieldValue
     * @param <T>
     */
    public static <T> void setField(T model, Field field, Object fieldValue) {
        try {
            field.setAccessible(true);
            field.set(model, fieldValue);
        } catch (Exception e) {
            //logger.warn("对象{}字段赋值失败", field.getName(), e);
        }
    }
}

