package com.comment.prettyInstance;

import java.lang.annotation.*;

/**
 * Created by Mtime on 2016/2/4.
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestableException {
    Class<? extends Throwable>[] value();
}
