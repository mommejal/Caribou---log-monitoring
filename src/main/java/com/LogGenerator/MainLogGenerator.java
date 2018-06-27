package com.LogGenerator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainLogGenerator {

	public static void main(String[] args) {
		File fileInput = new File("server.log");
	    File fileOutput = new File("logs.log");
		FileReader fr;
		FileWriter fw;
	    try {
	    	//Création de l'objet de lecture
	    	fr = new FileReader(fileInput);
	    	
	    	String str = null;
	    	boolean t = true;
	    	while (t) {
	    		Thread.sleep((long) (2000*Math.random()));
	    		str = readLine(fr);
	    		System.out.println("j'écris :" + str);
	    		if (str=="")
	    			t = false;
	    		else {
	    			fw = new FileWriter(fileOutput,true);
	    			fw.write(str + System.lineSeparator());
	    			fw.close();
	    			// fw.flush();
	    		}
	    	}
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (InterruptedException e) {
	    	System.out.println("interrompu");
	    }
	    System.out.println("fini !");	
	}


	private static String readLine(FileReader fr) throws IOException {
		String res = "";
		int i;
		while ((i = fr.read()) != -1 && (char)i != '\n')
			res += (char)i;
		return res;
	}
}
