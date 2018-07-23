package com.caribou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages= {"com" /*,"com.appweb.controllers","com.appweb.mavoutput"*/})
public class CaribouApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaribouApplication.class, args);
	}
}
