package com.appweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.applications.App;

public abstract class Displayer {
	@Autowired public ModelAndView mav;
}
