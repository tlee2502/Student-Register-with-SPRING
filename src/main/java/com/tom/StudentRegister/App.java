package com.tom.studentregister;

import com.tom.studentregister.controller.Controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    
    public static void main(String[] args) {
        
        ApplicationContext appContext
                = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Controller controller = new Controller(myService, myView);
        controller.run();
    }
    
}
