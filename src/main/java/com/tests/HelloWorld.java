package com.tests;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("hw")
@Scope("singleton")
public class HelloWorld {
	private String message;

	public HelloWorld(String str) {
		message = str;
	}
	
	public void setMessage(String message){
		this.message  = message;
	}
	public void getMessage(){
		System.out.println("Your Message : " + message);
	}
}
