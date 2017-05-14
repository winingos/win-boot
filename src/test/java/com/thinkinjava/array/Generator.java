package com.thinkinjava.array;

/**
 * Created by Administrator on 17/5/14.
 */
@FunctionalInterface
public interface Generator<T> {
    T  next();
}
