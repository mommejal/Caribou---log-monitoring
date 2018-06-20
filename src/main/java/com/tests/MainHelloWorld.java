package com.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class MainHelloWorld {
   public static void main(String[] args) {
	  ApplicationContext context = SpringApplication.run(MainHelloWorld.class,args);
      HelloWorld obj = (HelloWorld) context.getBean("hw");
      obj.setMessage("Hello World !");
      obj.getMessage();
   }
}





