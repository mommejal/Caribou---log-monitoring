//package com.bdd;
//
//import java.io.IOException;
//import java.util.Queue;
//
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import com.caribou.Logs;
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//public class GestionBdd {
//
//	public Queue<String> receptionLog(String jsoninstring) {
//		//MOI je voudrais recevoir un jsoninstring et le splitter pour obtenir mes logs
//		ObjectMapper mapper = new ObjectMapper();
//
//		try {
//			// Réception des logs sous forme JSON et renvoie la PriorityQueue voulue
//			Queue<String> logs = mapper.readValue(jsoninstring, PriorityQueue<String>.class);
//			return logs;
//
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//
//}
//
//
