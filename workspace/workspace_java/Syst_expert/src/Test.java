import java.io.IOException;

import gestionFichier.FileWR;


public class Test {

	public static void main (String[] args) throws IOException{
		FileWR f = new FileWR();
		
		f.writeLine("Bonjour", "text.txt");
		f.realLines("text.txt");
	}
}
