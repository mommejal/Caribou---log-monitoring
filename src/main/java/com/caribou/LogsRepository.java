package com.caribou;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface LogsRepository extends MongoRepository<Logs, Integer>/*, QuerydslPredicateExecutor<Logs>*/{
	public List<Logs> findLogsBySeveritylvl(String severitylvl);
	public List<Logs> findOneByIdlog(int idlog);
//	public void viderBdd();
//	public void  remove();
}
