package com.dao;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bdd.LightLog;

public interface LogDAO extends MongoRepository<LightLog, String>/* , QuerydslPredicateExecutor<LightLog> */ {
//	public HashSet<LightLog> findLightLogBySeveritylvl(String severitylvl);

	public List<LightLog> findLightLogBy_id(int id);

	@Query("{ 'msg' : { $regex: ?0 } }")
	HashSet<LightLog> findLightLogByRegexpContentOrderBy_id(String regexp);
	@Query
	boolean existsBy_id(int id);
	void delete(LightLog log); // A voir si j'ai besoin de ca
	// @Query("{ '_id' : )
}
