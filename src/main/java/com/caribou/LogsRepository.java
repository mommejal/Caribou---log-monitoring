package com.caribou;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface LogsRepository extends MongoRepository<Logs, Integer>{
	public List<Logs> findLogsBySeveritylvl(String severitylvl);
//	public void viderBdd();
//	public void  remove();
}
