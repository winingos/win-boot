package com.win.controller.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 17/8/27.
 */
@Setter
@Getter
@Component("userMe")
public class UserMessage {
    @Value("2013-09-09")
    private Date date1;
}
