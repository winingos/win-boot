package com.win.common.dto.support;

/**
 * Created by ning.wang on 2016/5/25.
 */

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


public class DtoReflectUtil {


    /**
     * 获取所有字段，解决了不能复制继承自父类的属性的问题
     * @param obj
     * @return
     */
    public static Map<String, Field> getFieldMapper(Object obj) {
        Class clazz = obj.getClass();
        Map<String, Field> mapper = new HashMap<>();
        Field[] fields = null;
        while(clazz != null && !Object.class.equals(clazz) && (fields = clazz.getDeclaredFields()) != null) {
            for(Field field : fields) {
                int modifiers = field.getModifiers();
                if(Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) continue;
                mapper.putIfAbsent(field.getName(), field);
            }
            clazz = clazz.getSuperclass();
        }
        return mapper;
    }
    public static void setFieldValue(Field field, Object obj, Object value) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (Exception e) { }
    }
    public static Object getFieldValue(Field field, Object obj) {
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (Exception e) { }
        return null;
    }
}

