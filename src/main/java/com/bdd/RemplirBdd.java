package com.bdd;

import java.util.ArrayDeque;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.caribou.Logs;
import com.caribou.LogsRepository;

@Component
@EnableMongoRepositories(basePackageClasses = com.caribou.LogsRepository.class)
public class RemplirBdd /*implements CommandLineRunner*/ {
	
	public void ajouterLogBdd(LogsRepository logsRepository, Logs log) {
		logsRepository.save(log);
	}

	public void remplir(ArrayDeque<String> logs, LogsRepository logsRepository) {
		String contenu = logs.pollLast();
//		int id = getId(contenu);
		int id=10;
		while (contenu != null) {
			ajouterLogBdd(logsRepository, new Logs(id, contenu));
			contenu = logs.pollLast();
			id++;
		}

	}
}

