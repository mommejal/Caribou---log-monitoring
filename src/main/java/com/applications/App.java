package com.applications;

import java.util.Collection;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.appweb.controllers.Displayer;
import com.dao.LogDAO;

public abstract class App {
	protected String name;
	protected Displayer displayer;
	
	public abstract ModelAndView afficher(Collection<String> args);

	@Autowired public LogDAO dao;
}
