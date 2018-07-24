package com.applications;

import java.util.Collection;

import org.springframework.web.servlet.ModelAndView;

import com.appweb.controllers.Displayer;

public abstract class App {
	protected String name;
	protected Displayer displayer;
	
	public abstract ModelAndView afficher(Collection<String> args);
}
