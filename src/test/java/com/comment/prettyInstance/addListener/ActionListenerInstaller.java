package com.comment.prettyInstance.addListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

/**
 * Created by Mtime on 2016/2/4.
 *
 */
public class ActionListenerInstaller {
    public static void isntall(Object object) throws IllegalAccessException, InstantiationException {
        for(Field field : object.getClass().getDeclaredFields()){
            // 如果该成员变量被ActionListenerFor标记了
            if(field.isAnnotationPresent(ActionListenerFor.class)){
                // 设置访问权限
                field.setAccessible(true);
                // 获取到成员变量的值
                AbstractButton targetButton = (AbstractButton)  field.get(object);
                // 获取到注解中的Listener
                Class<? extends ActionListener> listener = field.getAnnotation(ActionListenerFor.class).listener();
                // 添加到成员变量中
                targetButton.addActionListener(listener.newInstance());
            }
        }
    }
}
