package com.applications;

import org.springframework.beans.factory.annotation.Autowired;

import com.appweb.controllers.Displayer;
import com.dao.LogDAO;

public abstract class App {
	protected String name;
	protected Displayer displayer;
	@Autowired public LogDAO dao;
}
