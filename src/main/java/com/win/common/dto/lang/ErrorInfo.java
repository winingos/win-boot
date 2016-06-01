package com.win.common.dto.lang;

/**
 * Created by ning.wang on 2016/6/1.
 * 定义错误信息接口。
 */
public interface ErrorInfo {
    /**
     * 错误代码。
     *
     * @return
     */
    int getCode();

    /**
     * 错误信息。
     *
     * @return
     */
    String getMessage();
}

