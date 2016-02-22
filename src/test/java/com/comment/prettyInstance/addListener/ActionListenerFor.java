package com.comment.prettyInstance.addListener;

import java.awt.event.ActionListener;
import java.lang.annotation.*;

/**
 * Created by Mtime on 2016/2/4.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ActionListenerFor {
    Class<? extends ActionListener> listener();
}
