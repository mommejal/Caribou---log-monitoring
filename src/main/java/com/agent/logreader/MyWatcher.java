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
import java.util.ArrayDeque;
import java.util.Queue;
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

		// TODO retour si echec ?
		param.maj(output);
	}

	public WatchService getService() {
		return service;
	}

	public void run() throws Exception {

		Queue<Queue<String>> logs = new ArrayDeque<>();
		long chrono = System.currentTimeMillis();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		boolean test;
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

							String line;

							Pattern startPattern = Pattern.compile(param.getRegexDebutLog());
							Pattern endPattern = Pattern.compile(param.getRegexFinLog());
							
							lines=0;
							
							while ((line = in.readLine()) != null && lines < param.getNbLignesDeSuite()) {
								lines++;
								characters += line.length() + System.lineSeparator().length();
								if (startPattern.matcher(line).find()) {

									Queue<String> message = new ArrayDeque<>();

									message.add(line);

									if (!param.getRegexFinLog().isEmpty()) {
										
										int linesBuff = 0, charactersBuff = 0;
										test = false;
										while ((line = in.readLine()) != null && !(test = endPattern.matcher(line).find())
												&& lines+linesBuff < param.getNbLignesDeSuite()) {
											message.add(line);
											linesBuff++;
											charactersBuff += line.length() + System.lineSeparator().length();;
										}
										if (test) {
											lines += linesBuff;
											characters += charactersBuff;
											
											logs.add(message);
										}
									} else 
										logs.add(message);
								}								
							}
							
							if (!logs.isEmpty() && System.currentTimeMillis() - chrono > param.getTpsVieMinStock()) {
								System.out.println("j'envoie ça");
								System.out.println(ow.writeValueAsString(logs));
								PostREST.postString(ow.writeValueAsString(logs), new URL(output + "/logIncome"));
								chrono = System.currentTimeMillis();
							}
							param.maj(output);
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
}
