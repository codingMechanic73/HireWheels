package com.upgrad.hirewheels;

import com.upgrad.hirewheels.services.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * This is a car booking application starting point
 */
@SpringBootApplication
public class HireWheelsApplication {
@Autowired
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(HireWheelsApplication.class, args);
//        InitService initService = ctx.getBean(InitService.class);
//        initService.start();
}

}
