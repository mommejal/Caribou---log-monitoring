//package com.bdd;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import com.dao.LogDAO;
//import com.log.LightLog;
//import com.mongodb.Mongo;
//
//@Component
//@EnableMongoRepositories(basePackageClasses = com.dao.LogDAO.class)
//@Repository
//public class RemplirBdd /* implements CommandLineRunner */ {
//	public LogDAO dao;
//	@Autowired Mongo mongo;
//	@Autowired MongoDbFactory mongoDbFactory;
//
//	public void ajouterLogBdd(LogDAO dao, LightLog log) {
//		dao.save(log);
//	}
//
//
//
////	public void remplir(ArrayDeque<String> logs, LogsRepository logsRepository) {
////		String contenu = logs.pollLast();
////
////		while (contenu != null) {
////			Logs tmp = new Logs(contenu);
////			int id = tmp.getId();
////			tmp.setId(id);
////			ajouterLogBdd(logsRepository, tmp);
////			contenu = logs.pollLast();
////		}
////	}
//
////	 public void remplir(ArrayDeque<String> logs, LogsRepository logsRepository) {
////	 String contenu = logs.pollLast();
////	// int id = getId(contenu);
////	 int id=10;
////	 while (contenu != null) {
////	 Logs tmp = new Logs(contenu);
////	 tmp.setIdlog(id);
////	 ajouterLogBdd(logsRepository, tmp);
////	 contenu = logs.pollLast();
////	 id++;
////	 }
////	 }
//}
