package com.win.controller.handler;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 17/8/27.
 */
public class DatePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        System.out.println("text = " + text);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(text);
            this.setValue(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
