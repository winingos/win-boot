package com.win.common.dto.reflect;


/**
 * 类型工具
 * Created by Mtime on 2016/2/26.
 */
public class ClassUtil {

    /**
     * 是否是基本类型或基本类型的封装类型
     * @param clazz
     * @return
     */
    public static boolean isPrimitive(Class clazz) {
        if(clazz == null) return false;
        return clazz.isPrimitive()
                || clazz.equals(Byte.class)
                || clazz.equals(Integer.class)
                || clazz.equals(Long.class)
                || clazz.equals(Float.class)
                || clazz.equals(Double.class)
                || clazz.equals(Character.class)
                || clazz.equals(Boolean.class)
                || clazz.equals(Short.class);
    }

    /**
     * 是否是int类型
     * @param clazz
     * @return
     */
    public static boolean isInt(Class clazz) {
        return clazz != null && ( clazz.equals(int.class) || clazz.equals(Integer.class) );
    }
    /**
     * 是否是long类型
     * @param clazz
     * @return
     */
    public static boolean isLong(Class clazz) {
        return clazz != null && ( clazz.equals(long.class) || clazz.equals(Long.class) );
    }
    /**
     * 是否是boolean类型
     * @param clazz
     * @return
     */
    public static boolean isBoolean(Class clazz) {
        return clazz != null && ( clazz.equals(boolean.class) || clazz.equals(Boolean.class) );
    }
    /**
     * 是否是short类型
     * @param clazz
     * @return
     */
    public static boolean isShort(Class clazz) {
        return clazz != null && ( clazz.equals(short.class) || clazz.equals(Short.class) );
    }
    /**
     * 是否是byte类型
     * @param clazz
     * @return
     */
    public static boolean isByte(Class clazz) {
        return clazz != null && ( clazz.equals(byte.class) || clazz.equals(Byte.class) );
    }
    /**
     * 是否是float类型
     * @param clazz
     * @return
     */
    public static boolean isFloat(Class clazz) {
        return clazz != null && ( clazz.equals(float.class) || clazz.equals(Float.class) );
    }
    /**
     * 是否是double类型
     * @param clazz
     * @return
     */
    public static boolean isDouble(Class clazz) {
        return clazz != null && ( clazz.equals(double.class) || clazz.equals(Double.class) );
    }
    /**
     * 是否是char类型
     * @param clazz
     * @return
     */
    public static boolean isChar(Class clazz) {
        return clazz != null && ( clazz.equals(char.class) || clazz.equals(Character.class) );
    }
    /**
     * 是否是String类型
     * @param clazz
     * @return
     */
    public static boolean isString(Class clazz) {
        return clazz != null && ( String.class.equals(clazz) );
    }

    /**
     * 实例化
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class tClass) {
        try {
            return (T) tClass.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

}
