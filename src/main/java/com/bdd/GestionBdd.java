//package com.bdd;
//
//import java.io.IOException;
//import java.util.ArrayDeque;
//import java.util.Arrays;
//
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//public class GestionBdd {
//
////	@SuppressWarnings("unchecked")
//	public ArrayDeque<String> receptionLog(String jsoninstring) {
//		//MOI je voudrais recevoir un jsoninstring et le splitter pour obtenir mes logs
//		ObjectMapper mapper = new ObjectMapper();
//		ArrayDeque<String> logs = new ArrayDeque<String>(1000); //FAIRE ATTENTION A LA TAILLE
//		try {
//			// Réception des logs sous forme JSON et renvoie la PriorityQueue voulue
////			logs = (ArrayDeque<String>)(mapper.readValue(jsoninstring, ArrayDeque.class));
//			logs = (ArrayDeque<String>)(mapper.readValue(jsoninstring,mapper.getTypeFactory().constructCollectionType(ArrayDeque.class, String.class)));
////			ArrayDeque<String> logs = Arrays.asList(mapper.readValue(jsoninstring, String[].class));
//			return logs;
//			
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
