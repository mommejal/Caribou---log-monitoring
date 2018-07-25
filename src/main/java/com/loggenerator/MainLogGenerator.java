package com.loggenerator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainLogGenerator {

//	public static void main(String[] args) {
//		generate(200);
//	}
	
	public static void generate(int n, String input, String output)  {
		File fileInput = new File(input);
	    File fileOutput = new File(output);
		FileReader fr;
		FileWriter fw;
	    try {
	    	//Création de l'objet de lecture
	    	fr = new FileReader(fileInput);
	    	
	    	// on vide le fichier
	    	fw = new FileWriter(fileOutput);
			fw.close();
	    	
	    	String str = null;
	    	boolean t = true;
	    	while (t) {
	    		Thread.sleep((long) (n*Math.random())); //n = nombre de ms max entre chaque ligne
	    		str = readLine(fr);
	    		System.out.println("j'ecris : " + str);
	    		if (str==null) //null permet de lire le ficheir meme en cas de saut de ligne en recanche la lecture ne s'arrete jamaais
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
