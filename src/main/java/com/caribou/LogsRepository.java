package com.caribou;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LogsRepository extends MongoRepository<Logs, String>/* , QuerydslPredicateExecutor<Logs> */ {
	public HashSet<Logs> findLogsBySeveritylvl(String severitylvl);

	public List<Logs> findLogsBy_id(int id);

	@Query("{ 'msg' : { $regex: ?0 } }")
	HashSet<Logs> findLogsByRegexpMsgOrderBy_id(String regexp);
	@Query
	boolean existsBy_id(int id);
	void delete(Logs log); // A voir si j'ai besoin de ca
	// @Query("{ '_id' : )
}
