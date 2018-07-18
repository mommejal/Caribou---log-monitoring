package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public abstract class Displayer extends App {
	@Autowired public ModelAndView mav;
}
