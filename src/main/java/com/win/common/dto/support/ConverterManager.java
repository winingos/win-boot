package com.win.common.dto.support;

import mtime.lark.util.convert.DateConverter;
import mtime.lark.util.lang.EnumDisplayNameSupport;
import mtime.lark.util.lang.EnumValueSupport;
import mtime.lark.util.lang.Enums;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sun Wang on 2016/2/2.
 */
public class ConverterManager {

    /**
     * 是否需要转换器
     * @param srcClass
     * @param destClass
     * @return
     */
    public static boolean requireConvert(Class srcClass, Class destClass) {
        if(srcClass.equals(destClass)
                || srcClass.isAssignableFrom(destClass)
                || destClass.isAssignableFrom(srcClass)) return false;
        if((Boolean.class.equals(srcClass) && boolean.class.equals(destClass))
                || (boolean.class.equals(srcClass) && Boolean.class.equals(destClass))) return false;
        if(Number.class.isAssignableFrom(srcClass) && Number.class.isAssignableFrom(destClass)) return false;
        return true;
    }

    /**
     * 寻找合适的转换器
     * @param srcClass
     * @param destClass
     * @return
     */
    public static Converter lookup(Class srcClass, Class destClass) {
        for(Converter converter : converters) {
            if(converter.support(srcClass, destClass)) {
                return converter;
            }
        }
        return null;
    }

    /**
     * 转换器初始化
     */
    private static final List<Converter> converters = new ArrayList<Converter>(){{
        // Long -- LocalDateTime
        this.add(new Converter<Long, LocalDateTime>() {

            @Override
            public boolean toSupport(Class srcClass, Class destClass) {
                return ( Long.class.equals(srcClass) || long.class.equals(srcClass) ) && LocalDateTime.class.equals(destClass);
            }

            @Override
            public boolean ofSupport(Class srcClass, Class destClass) {
                return ( Long.class.equals(destClass) || long.class.equals(destClass) ) && LocalDateTime.class.equals(srcClass);
            }

            @Override
            public LocalDateTime to(Long value, Class localDateTimeClass) {
                return value == null || value.longValue() == 0 ? null : DateConverter.ofEpochMilli(value.longValue());
            }

            @Override
            public Long of(LocalDateTime value, Class longClass) {
                return value == null ? Long.valueOf(0) : DateConverter.toEpochMilli(value);
            }
        });
        // Long -- LocalDate
        this.add(new Converter<Long, LocalDate>() {
            @Override
            public boolean toSupport(Class srcClass, Class destClass) {
                return ( Long.class.equals(srcClass) || long.class.equals(srcClass) ) && LocalDate.class.equals(destClass);
            }

            @Override
            public boolean ofSupport(Class srcClass, Class destClass) {
                return ( Long.class.equals(destClass) || long.class.equals(destClass) ) && LocalDate.class.equals(srcClass);
            }

            @Override
            public LocalDate to(Long value, Class localDateClass) {
                return value == null || value.longValue() == 0 ? null : DateConverter.toLocalDate(value.longValue());
            }

            @Override
            public Long of(LocalDate value, Class longClass) {
                return value == null ? Long.valueOf(0) : DateConverter.toEpochMilli(value);
            }
        });
        // LocalDate -- LocalDateTime
        this.add(new Converter<LocalDate, LocalDateTime>() {
            @Override
            public boolean toSupport(Class srcClass, Class destClass) {
                return LocalDate.class.equals(srcClass) && LocalDateTime.class.equals(destClass);
            }

            @Override
            public boolean ofSupport(Class srcClass, Class destClass) {
                return LocalDate.class.equals(destClass) && LocalDateTime.class.equals(srcClass);
            }

            @Override
            public LocalDateTime to(LocalDate value, Class kClass) {
                return value == null ? null : DateConverter.ofEpochMilli(DateConverter.toEpochMilli(value));
            }

            @Override
            public LocalDate of(LocalDateTime value, Class tClass) {
                return value == null ? null : DateConverter.toLocalDate(DateConverter.toEpochMilli(value));
            }
        });
        // Integer -- EnumValueSupport
        this.add(new Converter<Integer, EnumValueSupport>() {
            @Override
            public boolean toSupport(Class srcClass, Class destClass) {
                return ( Integer.class.equals(srcClass) || int.class.equals(srcClass) ) && EnumValueSupport.class.isAssignableFrom(destClass);
            }

            @Override
            public boolean ofSupport(Class srcClass, Class destClass) {
                return ( Integer.class.equals(destClass) || int.class.equals(destClass) ) && EnumValueSupport.class.isAssignableFrom(srcClass);
            }

            @Override
            public EnumValueSupport to(Integer value, Class enumValueSupportClass) {
                if(value == null || value.intValue() == 0) return null;
                return Enums.valueOf(enumValueSupportClass, value, false);
            }

            @Override
            public Integer of(EnumValueSupport value, Class integerClass) {
                return value == null ? Integer.valueOf(0) : value.value();
            }
        });
        // String -- EnumValueSupport
        this.add(new Converter<String, EnumDisplayNameSupport>() {
            @Override
            public boolean toSupport(Class srcClass, Class destClass) {
                return String.class.equals(srcClass)
                        && EnumDisplayNameSupport.class.isAssignableFrom(destClass)
                        && Enum.class.isAssignableFrom(destClass);
            }

            @Override
            public boolean ofSupport(Class srcClass, Class destClass) {
                return String.class.equals(destClass)
                        && EnumDisplayNameSupport.class.isAssignableFrom(srcClass)
                        && Enum.class.isAssignableFrom(srcClass);
            }

            @Override
            public EnumDisplayNameSupport to(String value, Class kClass) {
                try {
                    if(value != null) {
                        Field[] fields = kClass.getDeclaredFields();
                        for(Field field : fields) {
                            int modifiers = field.getModifiers();
                            Class fieldType = field.getType();
                            if(Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)
                                    && EnumDisplayNameSupport.class.isAssignableFrom(fieldType)) {
                                EnumDisplayNameSupport fieldValue = (EnumDisplayNameSupport) field.get(null);
                                if( value.equals(fieldValue.displayName()) ) return fieldValue;
                            }
                        }
                    }
                } catch (Exception e) { }
                return null;
            }

            @Override
            public String of(EnumDisplayNameSupport value, Class tClass) {
                return value == null ? null : value.displayName();
            }
        });
        //LocalDateTime-String yyyy-MM-dd HH:mm:ss
        boolean add = this.add(new Converter<LocalDateTime,String>() {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            @Override
            public boolean toSupport(Class srcClass, Class destClass) {
                return LocalDateTime.class.equals(srcClass)&&String.class.equals(destClass);
            }

            @Override
            public boolean ofSupport(Class srcClass, Class destClass) {
                return String.class.equals(srcClass)&&LocalDateTime.class.equals(destClass);
            }

            @Override
            public String to(LocalDateTime value, Class kClass) {
                return value.format(formatter);
            }

            @Override
            public LocalDateTime of(String value, Class tClass) {

                return LocalDateTime.parse(value, formatter);
            }
        });
    }};

}
