package com.caribou;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface LogsRepository extends MongoRepository<Logs, Integer>{

}
