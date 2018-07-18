package com.appweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.LogDAO;

@Component
public abstract class AbstractController {
	
	@Autowired
	LogDAO dao;
//	@autowired
//	AgentsManagers agentsManagers 
}
