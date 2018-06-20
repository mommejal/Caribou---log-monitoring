package com.tests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean
	HelloWorld getHelloworld() {
		return new HelloWorld("Coucou !!");
	}
}
