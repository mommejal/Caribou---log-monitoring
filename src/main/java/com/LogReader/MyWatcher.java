package com.LogReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.regex.Pattern;

public class MyWatcher {

	protected WatchService service;
	protected int lines = 0;
	protected int characters = 0;
	
	protected Pattern pattern;
	protected Path logPath;
	protected Path output;
	
	MyWatcher(Path logPath, Pattern pattern, Path output) throws IOException {
		this.service = FileSystems.getDefault().newWatchService();
		this.pattern = pattern;
		this.logPath = logPath;
		this.output = output;
	}
	
	public WatchService getService() {
		return service;
	}
	
	public void run() throws Exception {
		
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
							String line, message = "";
							//Pattern p = Pattern.compile("Outbound Message|Inbound Message");
							in.skip(characters);
							while ((line = in.readLine()) != null) {
								lines++;
								characters += line.length() + System.lineSeparator().length();
								message += line + System.lineSeparator();
						/*		if (p.matcher(line).find()) {
									// Do something
									Stack<String> message = new Stack<>();

									message.push(line);
									for (int i = 0; i < 3 && (line = in.readLine()) != null; i++) {

										message.push(line);
									}
									analyser.take(message);
								} 	*/
							}
							/*if (message == "")
								System.out.println("le message est vide ??");
							else */
							 if (message != ""){
								message = message.substring(0, message.length() -1);
								if (pattern.matcher(message).find())
									System.out.println("\nEnvoyer à "+ output.toString() +":\n" + message);
									// TODO Envoyer
								else
									System.out.println("\nNe pas envoyer :\n" + message);
							}
						}
					}
				}
				key.reset();
			} while (true);
		} catch (IOException |

				InterruptedException ex) {
			//Logger.getLogger(LogScanner.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
			ex.printStackTrace();
		}
	}
	
	
	
}
