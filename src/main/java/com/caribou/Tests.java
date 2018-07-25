package com.caribou;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.agent.paramagent.ParamAgentToManage;

public class Tests {

	@Autowired
	Map<String, ParamAgentToManage> agents;
	
	public static void main(String[] args) {
		
	}
}
