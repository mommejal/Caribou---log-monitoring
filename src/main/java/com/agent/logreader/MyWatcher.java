package com.agent.logreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.agent.ParamAgent;
import com.agent.exchanges.PostREST;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class MyWatcher {

	protected WatchService service;
	protected int lines = 0;
	protected int characters = 0;

	protected ParamAgent param;
	protected Path logPath;
	protected String output;

	MyWatcher(Path logPath, ParamAgent paramDefault, String outputURL) throws IOException {
		super();
		this.service = FileSystems.getDefault().newWatchService();
		this.param = paramDefault;
		this.logPath = logPath;
		this.output = outputURL;
		
		//TODO retour si echec ?
		param.majParam(output);
	}

	public WatchService getService() {
		return service;
	}

	public void run() throws Exception {
		
		Queue<String> logs = new PriorityQueue<>();
		long chrono = System.currentTimeMillis();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			do {
				System.out.println("Waiting...");
				WatchKey key = service.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					@SuppressWarnings("unchecked")
					WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
					Path path = pathEvent.context();
					if (path.equals(logPath)) {
						try (BufferedReader in = new BufferedReader(new FileReader(pathEvent.context().toFile()))) {
							in.skip(characters);
							
							logs.addAll(getLogs(in));
							
							if (!logs.isEmpty() 
									&& System.currentTimeMillis() - chrono < param.getTpsVieMinStock()) {
								//message = message.substring(0, message.length() - 1);
								
								param.majParam(output);
								System.out.println("j'envoie ça");
								PostREST.postString(ow.writeValueAsString(logs), new URL(output + "/logIncome"));
								
								/* if (Pattern.compile(param.getRegexFiltre()).matcher(message).find()) {
									System.out.println("Envoyer à " + output.toString() + ":\n" + message);
									PostREST.postString(message, new URL(output + "/logIncome"));
								} else
									System.out.println("Ne pas envoyer :\n" + message); */
							}
						}
					}
					key.reset();
				}
			} while (true);
		} catch (IOException | InterruptedException ex) {
			// Logger.getLogger(LogScanner.class.getName()).log(Level.SEVERE,
			// ex.getMessage(), ex);
			ex.printStackTrace();
		}
	}

	private Queue<String> getLogs(BufferedReader in) throws InterruptedException {
		Queue<String> res = new PriorityQueue<String>();
		String currentLog;
		long startTime = System.currentTimeMillis();
		while (startTime + param.getTpsAttenteLog() < System.currentTimeMillis()) {
			try  {
				currentLog = getNextLog(in,startTime);
				if (currentLog!="")
					if (Pattern.compile(param.getRegexFiltre()).matcher(currentLog).find())
						res.add(currentLog);
				else
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	private String getNextLog(BufferedReader in, long startTime) throws IOException, InterruptedException {
		String line, buffer = "";
		int buffLines = 0;
		int buffCharac = 0;
		Matcher matcher;
		Pattern pattern = Pattern.compile(param.getRegexDebutLog());
		do  {
			line = in.readLine();
			if (line == null)
				Thread.sleep(param.getTpsCheckLog());
			else {  
				buffLines++;
				buffCharac += line.length() + System.lineSeparator().length();		
				buffer += line + System.lineSeparator().length();
				matcher = pattern.matcher(buffer);
				if (matcher.find()) break;
			}
			if (startTime + param.getTpsAttenteLog() < System.currentTimeMillis())
				return null;
		} while (true);
		
		System.out.println("je tiens le début");
		
		buffer = buffer.substring(matcher.start(),buffer.length()); // ça casse ou ça passe
		
		pattern = Pattern.compile(param.getRegexFinLog());
		
		do  {
			line = in.readLine();
			if (line == null)
				Thread.sleep(param.getTpsCheckLog());
			else {  
				buffLines++;
				buffCharac += line.length() + System.lineSeparator().length();		
				buffer += line + System.lineSeparator().length();
				matcher = pattern.matcher(buffer);
				if (matcher.find()) break;
			}
			if (startTime + param.getTpsAttenteLog() < System.currentTimeMillis())
				return null;
		} while (true);

		buffer = buffer.substring(0,buffer.length()-1); // pour enlever le saut de ligne final
		
		lines += buffLines;
		characters += buffCharac;
		
		System.out.println("j'en ai un !");
		
		return buffer;
	}
}
