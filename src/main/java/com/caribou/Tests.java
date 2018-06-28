package com.caribou;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tests {
	private static Pattern pattern;
	private static Matcher matcher;

	public static void main(String[] args) {

		String input = "10:55:47,539 INFO  [org.apache.cxf.interceptor.LoggingInInterceptor] (default task-158) Inbound Message\r\n"
				+ "----------------------------\r\n" + "ID: 1041\r\n"
				+ "Address: http://SALES-FLOW:18080/SalesFlow/services/rest/salesflows\r\n" + "Encoding: ISO-8859-1\r\n"
				+ "Http-Method: POST\r\n" + "Content-Type: application/json\r\n"
				+ "Headers: {Accept=[application/json], accept-encoding=[UTF-8,deflate], Authorization=[Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJPMy15R25uN1IweVQzYjNxTkZUR2FIYVFtVWV0NXZRWUJnS1RsTmRnRnd3In0.eyJqdGkiOiIwMWRmODdkMi02YmMxLTRmMDgtYWQ5NS04ZjMzY2ZiYThmOGQiLCJleHAiOjE1Mjg3MDc2NDcsIm5iZiI6MCwiaWF0IjoxNTI4NzA3MzQ3LCJpc3MiOiJodHRwOi8vU0VDVVJJVFk6OTA4MC9hdXRoL3JlYWxtcy9SZWFsbUFYQSIsImF1ZCI6InB1YmxpYy1jbGllbnQiLCJzdWIiOiI0YTkzNWJjYi0yOGZiLTQwZTUtYjFlYi01ZjQ1YjM4YWJhN2MiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJwdWJsaWMtY2xpZW50IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiOTNmODhmMTMtYTkzYy00OWI1LThjZjktMGNkNjExMDk2ZGM0IiwiYWNyIjoiMSIsImNsaWVudF9zZXNzaW9uIjoiNDYwNmY5ZjAtOGNmMC00ZWFjLTg0ZGQtOTEwN2ViYjg3NmI2IiwiYWxsb3dlZC1vcmlnaW5zIjpbXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIk1pZC1PZmZpY2VyIiwiYWRtaW4iLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJ2aWV3LXByb2ZpbGUiXX19LCJyb2xlcyI6WyJNaWQtT2ZmaWNlciIsIm9mZmxpbmVfYWNjZXNzIiwiYWRtaW4iLCJ1bWFfYXV0aG9yaXphdGlvbiJdLCJuYW1lIjoiIiwiZ3JvdXBzIjpbXSwicHJlZmVycmVkX3VzZXJuYW1lIjoiYnJva2VyMSJ9.vx7hDB7Q2lfNEwm9s_Nopzx58g4BgkuTprzQbIR1FzjYGM9mGEomt9YMdtBGMZvOBNkAftMp13n7uI4jBCdZ8vCTIwuvQ5HDBwsRlEDgeW8Ohjpu0RYDoJirLPYTdx4TcH8ScIodPoc0gfgi989BWrvkjES-eXCqjw9bmsJGAPNoehkOiFqK4LIPLyPvMZPUvKDZe8WlxIkIh_oPxqvKjLK42pdkCZSY4xV5XQ5_AOKwo-sUsDRE4g-AOVY2uRRZobxZ3uVLvfReqe8PbNKcxTlJvS41UtsqQ5eO9OS8p0yyQYScyclG3Bh8el8xf0ptfV_rWMOYKtJPUWLkH5jhNw], cache-control=[no-cache], connection=[Keep-Alive], Content-Length=[2131], content-type=[application/json], correlationid=[c70a9070-d19c-4766-895a-741003d8a77a, c70a9070-d19c-4766-895a-741003d8a77a], host=[SALES-FLOW:18080], pragma=[no-cache], user-agent=[Apache-CXF/3.1.7], user-profile=[FR]}\r\n"
				+ "Payload: {\"type\":\"com.vermeg.webservice.SaleFlowInput\",\"partnerIdentifier\":\"00131126\",\"prospectLastName\":\"\",\"prospectFirstName\":\"\",\"commercialProductIdentifier\":\"AXA-PLCI_0510\",\"packageIdentifier\":\"PLCI_ordianire\",\"simulationForm\":{\"type\":\"com.vermeg.forminput.FormInput\",\"identifier\":\"\",\"definitionIdentifier\":\"240011\",\"questions\":[{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"daBeginDate\",\"definitionIdentifier\":\"daBeginDate|null\",\"answerValue\":\"2018-06-08\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"language\",\"definitionIdentifier\":\"language|null\",\"answerValue\":\"FR\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"daBirthDate\",\"definitionIdentifier\":\"daBirthDate|null\",\"answerValue\":\"1980-01-01\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"AgeTerm\",\"definitionIdentifier\":\"AgeTerm|null\",\"answerValue\":\"67\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"targetTypeChoice\",\"definitionIdentifier\":\"targetTypeChoice|null\",\"answerValue\":\"MaximumIndexe\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"lumpSumTarget\",\"definitionIdentifier\":\"lumpSumTarget|null\",\"answerValue\":\"\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"Eprofession\",\"definitionIdentifier\":\"Eprofession|null\",\"answerValue\":\"actuaire\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"RefIncomeN3\",\"definitionIdentifier\":\"RefIncomeN3|null\",\"answerValue\":\"50000\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"refIncome\",\"definitionIdentifier\":\"refIncome|null\",\"answerValue\":\"50000\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"Smoker\",\"definitionIdentifier\":\"Smoker|null\",\"answerValue\":\"false\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"TechSettings\",\"definitionIdentifier\":\"TechSettings|null\",\"answerValue\":\"false\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"CommissionInvest\",\"definitionIdentifier\":\"CommissionInvest|null\",\"answerValue\":\"0.06\"},{\"type\":\"com.vermeg.forminput.QuestionInput\",\"identifier\":\"CommissionRateRiders\",\"definitionIdentifier\":\"CommissionRateRiders|null\",\"answerValue\":\"0.15\"}]}}\r\n"
				+ "--------------------------------------\r\n"
				+ "10:55:47,541 INFO  [stdout] (default task-158) ***************************************************\r\n"
				+ "10:55:47,541 INFO  [stdout] (default task-158) FR\r\n"
				+ "10:55:47,541 INFO  [stdout] (default task-158) ***************************************************\r\n"
				+ "10:55:47,544 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:544 |Palmyra : DEBUG |InvocationLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: requesting URL >> http://PACK-MANAGER:28080/PackManager/services/rest/form-definitions/240011\r\n"
				+ "10:55:47,567 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:567 |Palmyra : INFO |RestLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: The request [ http://PACK-MANAGER:28080/PackManager/services/rest/form-definitions/240011 ] has ended in 0.0019776135 seconds with status :200\r\n"
				+ "10:55:47,568 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:568 |Palmyra : DEBUG |InvocationLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: requesting URL ended >> http://PACK-MANAGER:28080/PackManager/services/rest/form-definitions/240011\r\n"
				+ "10:55:47,591 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:591 |Palmyra : DEBUG |InvocationLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: requesting URL >> http://PACK-MANAGER:28080/PackManager/services/rest/form-definitions/240011/validate\r\n"
				+ "10:55:47,605 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:605 |Palmyra : INFO |RestLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: The request [ http://PACK-MANAGER:28080/PackManager/services/rest/form-definitions/240011/validate ] has ended in 9.941702E-4 seconds with status :200\r\n"
				+ "10:55:47,605 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:605 |Palmyra : DEBUG |InvocationLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: requesting URL ended >> http://PACK-MANAGER:28080/PackManager/services/rest/form-definitions/240011/validate\r\n"
				+ "10:55:47,643 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:642 |Palmyra : INFO |RestLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: The request [ http://PACK-MANAGER:28080/PackManager/services/rest/forms/ ] has ended in 0.002977204 seconds with status :200\r\n"
				+ "10:55:47,721 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:721 |Palmyra : INFO |RestLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: The request [ http://PACK-MANAGER:28080/PackManager/services/rest/products/AXA-PLCI_0510/packages/PLCI_ordianire/initialize ] has ended in 0.0067910852 seconds with status :200\r\n"
				+ "10:55:47,927 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:926 |Palmyra : INFO |RestLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: The request [ http://PACK-MANAGER:28080/PackManager/services/rest/forms/ ] has ended in 0.0129467944 seconds with status :200\r\n"
				+ "10:55:47,958 INFO  [stdout] (default task-158) juin 11, 2018 10:55:47:958 |Palmyra : INFO |RestLogger-[default task-158]: |c70a9070-d19c-4766-895a-741003d8a77a| SalesFlow:broker1: The request [ http://SOLIFE:31080/solife/services/api/strategy-definitions/GUARANTEED_PLCI/compute-rates ] has ended in 0.0015051904 seconds with status :200\r\n"
				+ "10:55:48,407 INFO  [org.apache.cxf.interceptor.LoggingOutInterceptor] (default task-158) Outbound Message\r\n"
				+ "---------------------------\r\n" + "ID: 1041\r\n" + "Response-Code: 200\r\n"
				+ "Content-Type: application/json\r\n"
				+ "Headers: {Content-Type=[application/json], Date=[Mon, 11 Jun 2018 08:55:48 GMT]}\r\n"
				+ "Payload: {\r\n" + "  \"type\" : \"com.vermeg.webservice.SalesFlowResponse\",\r\n"
				+ "  \"data\" : {\r\n" + "    \"type\" : \"com.vermeg.webservice.SalesFlowIdentifier\",\r\n"
				+ "    \"identifier\" : 640007027\r\n" + "  }\r\n" + "}" + "---------------------------";
//		(?s).*-{10,}\r\n(?s).*-{10,}\r\n(?s).*-{10,}\r\n(?s).*-{10,}
		pattern = Pattern.compile("(?:(?s).*)-{10,}\r\nID: ");
		matcher = pattern.matcher(input);
		matcher.find();
		int debut = matcher.end();
		System.out.println(debut);
		pattern = Pattern.compile("(?s).*------\r\nID: [0-9]*\r\n");
		matcher = pattern.matcher(input);
		matcher.find();
		int fin = matcher.end();
		System.out.println(fin);
		String res = new String();
		res = input.substring(debut, fin);
		System.out.println(res);
		// "(?:(?s).*)------\r\nID: ";
		// Matcher a = Pattern.compile(patternbeg).matcher(input);
		// if (a.find()) {
		// int debut = a.end();
		// String patternend = "(?s).*------\r\nID: [0-9]*\r\n";
		// Matcher b = Pattern.compile(patternend).matcher(input);
		// int fin = b.end();
		// String res = new String();
		// res = res.substring(debut, fin);
		// System.out.println(res);
		
	}
}
