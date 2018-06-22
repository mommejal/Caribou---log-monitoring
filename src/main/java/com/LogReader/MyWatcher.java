package com.LogReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Map;
import java.util.regex.Pattern;

public class MyWatcher {

	protected WatchService service;
	protected int lines = 0;
	protected int characters = 0;

	protected String regexPattern;
	protected Path logPath;
	protected String output;

	MyWatcher(Path logPath, String regexPattern, String outputPath) throws IOException {
		this.service = FileSystems.getDefault().newWatchService();
		this.regexPattern = regexPattern;
		this.logPath = logPath;
		this.output = outputPath;
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
							in.skip(characters);
							while ((line = in.readLine()) != null) {
								lines++;
								characters += line.length() + System.lineSeparator().length();
								message += line + System.lineSeparator();
							}
							if (message != "") {
								message = message.substring(0, message.length() - 1);
								majRegex();
								if (Pattern.compile(regexPattern).matcher(message).find()) {
									System.out.println("Envoyer à " + output.toString() + ":\n" + message);
									envoyer(message);
								} else
									System.out.println("Ne pas envoyer :\n" + message);
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
	
	
	// TODO ranger un peu ce bazar
	

	private void envoyer(String input) {
		try {
			URL url = new URL(output + "/logIncome");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/plain");

			OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
			os.write(input);
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private boolean majRegex() {
		boolean resok = false;
		try {

			URL url = new URL(output + "/regexOutput");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			String res = "";
			while ((inputLine = in.readLine()) != null)
				res += inputLine;
			in.close();

			resok = res != regexPattern;
			regexPattern = res;

		} catch (MalformedURLException e) {
			
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
		System.out.println("la regex du filtre est : " + regexPattern);
		return resok;
	}

	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}

}
