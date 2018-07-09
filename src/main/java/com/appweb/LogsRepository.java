package com.appweb;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface LogsRepository extends MongoRepository<Logs, Integer>{
//	public void viderBdd();
//	public void  remove();
}
