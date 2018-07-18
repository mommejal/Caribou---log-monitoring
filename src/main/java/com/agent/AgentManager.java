package com.agent;

import java.util.Hashtable;

import com.agent.paramagent.ParamAgentToManage;

public class AgentManager extends Hashtable<String, ParamAgentToManage>{

	/**
	 * je sais pas ce qu'est cet ID mais eclipse a l'air de dire que c'est important
	 */
	private static final long serialVersionUID = 1184618849954242784L;
	
	public void insert(ParamAgentToManage pa) {
		put(pa.getId(), pa);
	}	
}
