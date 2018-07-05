package com.agent.exchanges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Queue;

public class GetREST {

	public static Queue<String> getAll(URL source) throws IOException {
		HttpURLConnection con = (HttpURLConnection) source.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		Queue<String> res = new ArrayDeque<String>();
		while ((inputLine = in.readLine()) != null)
			res.add(inputLine);
		in.close();
		return res;
	}
}
