package com.win.common.dto;


import com.win.common.dto.log.Logger;
import com.win.common.dto.log.LoggerManager;
import com.win.common.dto.reflect.ClassUtil;
import com.win.common.dto.reflect.ReflectUtil;
import com.win.common.dto.support.Converter;
import com.win.common.dto.support.ConverterManager;


import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DtoUtils {

    private static Logger logger = LoggerManager.getLogger(DtoUtils.class);
    private static Object getInvoke( PropertyDescriptor srcProperty,String property) {
        try {
            return srcProperty.getReadMethod().invoke(srcProperty,property);
        } catch (IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static void  setInvoke(PropertyDescriptor srcProperty,String value) {
        try {
            srcProperty.getWriteMethod().invoke(srcProperty,value);
        } catch (IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static <T> T clone(Object src,T dest){
        if(src==null) return null;
        Objects.requireNonNull(dest, "目标对象不能为空");
        Map<String, PropertyDescriptor> srcmaper = getPropertyMaper(src);
        Map<String, PropertyDescriptor> destmaper = getPropertyMaper(dest);
//        destmaper.keySet().stream()
//                .filter(d->!srcmaper.keySet().contains(d))
//                .filter()
//                .map(d->{
//                    PropertyDescriptor srcProperty = srcmaper.get(d);
//                    PropertyDescriptor destProperty = destmaper.get(d);
//                    Class<?> srcType = srcProperty.getPropertyType();
//                    Class<?> destType = destProperty.getPropertyType();
//                    Object value = getInvoke(srcProperty,d);
//                    if (Collection.class.isAssignableFrom(destType)){ // 深度复制集合类型
//                       // if (!Collection.class.isAssignableFrom(srcType)) continue;
////                        if (value == null) {
////                            setInvoke(destProperty, null);
////                            continue;
////                        }
//
//
//                    }
//                })
        return dest;
    }



    /**
     * 复制对象属性
     *
     * @param src
     * @param dest
     * @param <T>
     * @return
     */
    public static <T> T copy(Object src, T dest) {
        if (src == null) return null;
        Objects.requireNonNull(dest, "目标对象不能为空");
        Map<String, Field> srcMap = getFieldMapper(src);
        Map<String, Field> destMap = getFieldMapper(dest);
        for (String fieldName : destMap.keySet()) {
            if (!srcMap.keySet().contains(fieldName)) continue;
            Field srcField = srcMap.get(fieldName),    destField = destMap.get(fieldName);
            Class srcFieldType = srcField.getType(),   destFieldType = destField.getType();
            Object value = getFieldValue(srcField, src);
            if (Collection.class.isAssignableFrom(destFieldType)) {
                // 深度复制集合类型
                if (!Collection.class.isAssignableFrom(srcFieldType)) continue;
                if (value == null) {
                    setFieldValue(destField, dest, null);
                    continue;
                } else {
                    Collection collection = (Collection) getFieldValue(destField, dest);
                    if (collection == null) {
                        collection = initCollection(destFieldType);
                        if (collection == null) continue;
                        setFieldValue(destField, dest, collection); //创建集合对象
                    }
                    Class destGenericType = ReflectUtil.getGenericType(destField);  //集合字段泛型
                    Class srcGenericType = ReflectUtil.getGenericType(srcField);  //集合字段泛型
                    if (destGenericType == null || srcGenericType == null) continue;
                    for (Object item : (Collection) value) {
                        Converter converter = ConverterManager.lookup(srcGenericType, destGenericType);
                        if (converter != null) {
                            collection.add(converter.convert(item, srcGenericType, destGenericType));
                        } else {
                            collection.add(copy(item, destGenericType));
                        }
                    }
                }
            } else if (Map.class.isAssignableFrom(destFieldType)) {

            } else {
                // 非集合类型
                try {
                    // 寻找转换器
                    Converter converter = ConverterManager.lookup(srcFieldType, destFieldType);
                    if (converter != null) {
                        value = converter.convert(value, srcFieldType, destFieldType);
                        setFieldValue(destField, dest, value);
                    } else if ((ClassUtil.isPrimitive(srcFieldType) && ClassUtil.isPrimitive(destFieldType))
                            || (srcFieldType.getName().startsWith("java.lang.") || destFieldType.getName().startsWith("java.lang."))
                            || srcFieldType.isAssignableFrom(destFieldType) || destFieldType.isAssignableFrom(srcFieldType)) {
                        // 强转
                        setFieldValue(destField, dest, value);
                    } else {
                        // 对象类型复制
                        setFieldValue(destField, dest, copy(value, destFieldType));
                    }
                } catch (Exception e) {
                    System.out.println("复制对象属性出错，属性名：" + fieldName);
                }
            }
        }
        return dest;
    }

    /**
     * 复制对象属性，自动创建对象实例
     *
     * @param src
     * @param dest
     * @param <T>
     * @return
     */
    public static <T> T copy(Object src, Class<T> dest) {
        try {
            if (src == null) return null;
            // 如果是基本类或基本类的封装类或者是String类型,直接强转
            if (ClassUtil.isPrimitive(dest) || ClassUtil.isString(dest)) return (T) src;
            if (dest.isAssignableFrom(src.getClass())) return (T) src;
            return copy(src, dest.newInstance());
        } catch (Exception e) {
//            logger.error("对象属性复制出错", e);
            throw new RuntimeException("对象复制出错", e);

        }
    }

    /**
     * 批量复制
     *
     * @param list
     * @param dest
     * @param <T>
     * @return
     */
    public static <T> List<T> copy(List<?> list, Class<T> dest) {
        if (list == null) return null;
        List<T> result = new ArrayList<>();
        for (Object item : list) {
            T t = copy(item, dest);
            if (t != null) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * pojo转map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> toMap(Object obj) {
        if (obj == null) return null;
        Map<String, Object> map = new HashMap<>();
        Map<String, Field> fieldMap = getFieldMapper(obj);
        for (String fieldName : fieldMap.keySet()) {
            Object value = getFieldValue(fieldMap.get(fieldName), obj);
            map.put(fieldName, value);
        }
        return map;
    }

    /**
     * 批量转Map
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> toMapList(List<?> list) {
        if (list == null) return null;
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object item : list) {
            Map<String, Object> tmp = toMap(item);
            if (tmp != null) {
                result.add(tmp);
            }
        }
        return result;
    }

    /**
     * 枚举值转map
     *
     * @return
     */
    public static <T extends Enum> Map<String, Object> toEnumMap(T obj) {
        if (obj == null) return null;
        Map<String, Object> map = new HashMap<>();
        map.put("name", obj.name());
//        if (obj instanceof EnumValueSupport) {
//            map.put("value", ((EnumValueSupport) obj).value());
//        }
//        if (obj instanceof EnumDisplayNameSupport) {
//            map.put("displayName", ((EnumDisplayNameSupport) obj).displayName());
//        }
        return map;
    }

    /**
     * 枚举类转map
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Enum> List<Map<String, Object>> toEnumMapList(Class<T> clazz) {
        if (clazz == null) return null;
        T[] enums = clazz.getEnumConstants();
        List<Map<String, Object>> list = new ArrayList<>();
        if (enums != null && enums.length > 0) {
            for (T t : enums) {
                Map<String, Object> map = toEnumMap(t);
                if (map != null) list.add(map);
            }
        }
        return list;
    }

    /**
     * 实例化集合对象
     *
     * @param clazz
     * @return
     */
    private static Collection initCollection(Class clazz) {
        if (List.class.isAssignableFrom(clazz)) {
            return new ArrayList<>();
        } else if (Set.class.isAssignableFrom(clazz)) {
            return new HashSet<>();
        }
        return null;
    }

    /**
     * 获取所有字段，此方法不能复制继承自父类的属性
     * @param obj
     * @return
     */
//    private static Map<String, Field> getFieldMapper(Object obj) {
//        Class clazz = obj.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//        Map<String, Field> mapper = new HashMap<>();
//        for(Field field : fields) {
//            int modifiers = field.getModifiers();
//            if(Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) continue;
//            mapper.put(field.getName(), field);
//        }
//        return mapper;
//    }

    /**
     * 获取所有字段，解决了不能复制继承自父类的属性的问题
     *
     * @param obj
     * @return
     */
    private static Map<String, Field> getFieldMapper(Object obj) {
        Class clazz = obj.getClass();
        Map<String, Field> mapper = new HashMap<>();
        Field[] fields = null;
        while (clazz != null && !Object.class.equals(clazz) && (fields = clazz.getDeclaredFields()) != null) {
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) continue;
                mapper.putIfAbsent(field.getName(), field);
            }
            clazz = clazz.getSuperclass();
        }
        return mapper;
    }

    static Map<String, PropertyDescriptor> getPropertyMaper(Object obj) {
        Map<String, PropertyDescriptor> map = new HashMap<>();
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
            map = Stream.of(propertyDescriptors)
                    .filter(p -> !p.getName().equalsIgnoreCase("class"))
                    .collect(Collectors.toConcurrentMap(s->s.getName(), p -> p,(s, k) -> k));
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static void setFieldValue(Field field, Object obj, Object value) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (Exception e) {
        }
    }

    private static Object getFieldValue(Field field, Object obj) {
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (Exception e) {
        }
        return null;
    }

}
