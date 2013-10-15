import java.io.IOException;

import gestionFichier.FileWR;



public class Test {
	
	public static void main(String[] args) throws IOException {
		FileWR f= new FileWR();
		String s = "Bonjour";
		f.writeLine(s, "test.txt");
		f.realLines("test.txt");
		
	}

}
