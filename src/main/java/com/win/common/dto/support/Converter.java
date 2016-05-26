package com.win.common.dto.support;



/**
 * Created by Sun Wang on 2016/2/2.
 */
public interface Converter<T, K> {

    /**
     * 是否支持两种类型单向转换
     * @param srcClass
     * @param destClass
     * @return
     */
    default boolean support(Class srcClass, Class destClass){
        return toSupport(srcClass, destClass) || ofSupport(srcClass, destClass);
    }

    boolean toSupport(Class srcClass, Class destClass);

    boolean ofSupport(Class srcClass, Class destClass);

    default Object convert(Object value, Class srcClass, Class destClass) {
        if(toSupport(srcClass, destClass)) {
            return this.to((T) value, destClass);
        } else if(ofSupport(srcClass, destClass)) {
            return this.of((K) value, destClass);
        }
        throw new RuntimeException("不支持的类型转换");
    }

    K to(T value, Class kClass);

    T of(K value, Class tClass);

}
