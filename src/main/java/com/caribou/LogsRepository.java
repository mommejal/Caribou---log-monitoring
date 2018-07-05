package com.caribou;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LogsRepository extends MongoRepository<Logs, Integer>/*, QuerydslPredicateExecutor<Logs>*/{
	public List<Logs> findLogsBySeveritylvl(String severitylvl);
	public List<Logs> findOneByIdlog(int idlog);
	@Query("{ 'msg' : { $regex: ?0 } }")
	List<Logs> findLogsByRegexpMsg(String regexp);
	
}
