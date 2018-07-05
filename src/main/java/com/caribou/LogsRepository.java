package com.caribou;

import java.util.HashSet;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LogsRepository extends MongoRepository<Logs, Integer>/*, QuerydslPredicateExecutor<Logs>*/{
	public HashSet<Logs> findLogsBySeveritylvl(String severitylvl);
	public HashSet<Logs> findLogsByIdlog(int idlog);
	@Query("{ 'msg' : { $regex: ?0 } }")
	HashSet<Logs> findLogsByRegexpMsg(String regexp);
}

