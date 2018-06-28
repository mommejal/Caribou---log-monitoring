package com.bdd;

import java.util.ArrayDeque;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.*;

import com.caribou.Logs;
import com.caribou.LogsRepository;

@Component
@EnableMongoRepositories(basePackageClasses = com.caribou.LogsRepository.class)
public class RemplirBdd /* implements CommandLineRunner */ {

	public void ajouterLogBdd(LogsRepository logsRepository, Logs log) {
		logsRepository.save(log);
	}
	
//	public void viderBdd(LogsRepository logsRepository) {
//		logsRepository.drop();
//	}

//	public void remplir(ArrayDeque<String> logs, LogsRepository logsRepository) {
//		String contenu = logs.pollLast();
//
//		while (contenu != null) {
//			Logs tmp = new Logs(contenu);
//			int id = tmp.getId();
//			tmp.setId(id);
//			ajouterLogBdd(logsRepository, tmp);
//			contenu = logs.pollLast();
//		}
//

	public void remplir(ArrayDeque<String> logs, LogsRepository logsRepository) {
		String contenu = logs.pollLast();
//		int id = getId(contenu);
		int id=10;
		while (contenu != null) {
			Logs tmp = new Logs(id,contenu);
			tmp.setId(id);
			ajouterLogBdd(logsRepository, tmp);
			contenu = logs.pollLast();
			id++;
		}
}
	}
