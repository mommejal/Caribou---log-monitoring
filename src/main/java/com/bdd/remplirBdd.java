//package com.bdd;
//
//import java.util.Queue;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.stereotype.Component;
//
//import com.caribou.Logs;
//import com.mongodb.DB;
//
//@Component
//@EnableMongoRepositories(basePackageClasses = com.caribou.LogsRepository.class)
//public class remplirBdd /*implements CommandLineRunner*/ {
//
//	public void remplir(Queue<String> logs) {
//		String contenu = logs.poll();
////		int id = getId(contenu);
//		int id=4;
//		while (contenu != null) {
//			mongoOperation.save(new Logs(id, contenu));
//			contenu = logs.poll();
//			id++;
//		}
//
//	}
//}
