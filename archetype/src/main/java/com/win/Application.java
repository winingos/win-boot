package com.win;

/**
 * Created by ning.wang on 2016/5/10.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {


    public static void main(String[] args) throws Exception {

        new SpringApplication(Application.class).run(args);
       //SpringApplication.run(Application.class,args);

    }

}