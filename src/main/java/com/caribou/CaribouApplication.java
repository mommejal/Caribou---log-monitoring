package com.caribou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class CaribouApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaribouApplication.class, args);
	}
}
