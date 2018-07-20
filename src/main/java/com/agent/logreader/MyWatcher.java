package com.agent.logreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Pattern;

import com.agent.exchanges.PostREST;
import com.agent.paramagent.ParamAgentToWork;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class MyWatcher {

	private WatchService service;
	private int lines = 0;
	private int characters = 0;

	private ParamAgentToWork param;

	MyWatcher(ParamAgentToWork param) throws IOException {
		this.service = FileSystems.getDefault().newWatchService();
		this.param = param;
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
					if (path.equals(Paths.get(param.getLogPath()))) {
						try (BufferedReader in = new BufferedReader(new FileReader(pathEvent.context().toFile()))) {

							in.skip(characters);

							String line;

							Pattern startPattern = Pattern.compile(param.getRegexDebutLog());
							Pattern endPattern = Pattern.compile(param.getRegexFinLog());
							
							lines=0;
							
							while ((line = in.readLine()) != null && lines < param.getNbLignesDeSuite()) {
								lines++;
								characters += line.length() + System.lineSeparator().length();
								
								System.out.println("dans : " + line);
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
							System.out.println("ouais je passe ici, on a " + logs.toString() + (System.currentTimeMillis() - chrono) + ">" + param.getTpsVieMinStock());
							if (!logs.isEmpty() && System.currentTimeMillis() - chrono > param.getTpsVieMinStock()) {
								System.out.println("j'envoie ça");
								System.out.println(ow.writeValueAsString(logs));
								PostREST.postString(ow.writeValueAsString(logs), new URL(param.getOutputPath() + "/logIncome?idAgent=" + param.getId()));
								chrono = System.currentTimeMillis();
								logs.clear();
							}
							param.majStandard(); //TODO trop souvent ?? mettre un 2eme chrono si trop lourd
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

	public boolean connect() {
		try {
			PostREST.postString(param.birthAnnouncement(), new URL(param.getOutputPath() + "/newAgent"));
			param.majStandard();
		} catch (IOException exception) {
			exception.printStackTrace();
			return false;
		}
		return true;
	}
}
