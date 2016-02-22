package com.comment.prettyInstance;

import java.lang.annotation.*;

/**
 * Created by Mtime on 2016/2/4.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Tag {
    String name() default "该叫啥才好呢?";

    String description() default "这家伙很懒, 啥也没留下...";
}
