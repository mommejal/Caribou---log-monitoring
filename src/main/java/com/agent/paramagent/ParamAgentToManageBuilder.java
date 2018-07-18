package com.agent.paramagent;

public class ParamAgentToManageBuilder {

	public static ParamAgentToManage build(String birthAnnouncement) {
		String[] data = birthAnnouncement.split(System.lineSeparator());
		return new ParamAgentToManage(data[0],data[1],data[2],data[3]);
	}
}
