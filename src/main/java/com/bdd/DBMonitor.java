package com.bdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;

import com.applications.monitor.AbstractMonitor;
import com.dao.LogDAO;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.Mongo;

public class DBMonitor extends AbstractMonitor{
	
	@Autowired
	Mongo mongo;
	@Autowired
	MongoDbFactory mongoDbFactory;
	@Autowired LogDAO dao;
	public void emptyDB() {
	mongo.dropDatabase(mongoDbFactory.getDb().getName());
	System.out.println("SUPPPRESSION DE LA BDD");
	}
	
	@SuppressWarnings("deprecation")
	public void afficherStorageSize() {
		DB db = mongo.getDB(mongoDbFactory.getDb().getName());
		System.out.println(mongoDbFactory.getDb().getName());
		CommandResult resultSet = db.getCollection("database").getStats();
		System.out.println(resultSet);
		System.out.println(resultSet.get("count"));
		System.out.println(resultSet.get("avgObjSize"));
	}
	
	}
