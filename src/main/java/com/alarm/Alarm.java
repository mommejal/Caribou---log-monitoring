package com.alarm;

import java.util.Collection;

import com.alarm.alarmaction.AlarmAction;
import com.alarm.trigger.Trigger;
import com.log.LightLog;

public class Alarm {
	
	private String name;
	private Collection<Trigger> conditions;
	private Collection<AlarmAction> actions;
	private Collection<LightLog> history;
	private boolean active = false;
	
	public Alarm(String name, Collection<Trigger> conditions, Collection<AlarmAction> actions) {
		super();
		this.name = name;
		this.conditions = conditions;
		this.actions = actions;
	}
	
	public boolean actualize() {
		if (active) {
			boolean ring = true;
			for (Trigger trigger : conditions)
				ring = ring && trigger.actualize();
			if (ring) {
				for (AlarmAction action : actions)
					action.send(name + " est en train de sonner !!");
				history.add(new LightLog("id de l'alarme "+name+" et manque d'infos :/", 
						(new java.util.Date().getTime()) + name + " sonne", "alarm "+name));
				return true;
			}
		}
		return false;
	}
	
	void start() {
		active = true;
	}
	
	void stop() {
		active = false;
	}
	
	

}
