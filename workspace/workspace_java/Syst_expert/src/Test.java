import java.io.IOException;

import gestionFichier.FileWR;


public class Test {
	public static void main (String[] args) throws IOException{

		FileWR f = new FileWR();
		
//		f.writeLine("Bonjour", "BF.txt");
//		f.writeLine("Bonjour", "BR.txt");
		f.realLines("BR.txt");
		f.realLines("BF.txt");
		
	}
}
