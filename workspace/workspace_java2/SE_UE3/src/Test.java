import java.io.IOException;
import java.util.ArrayList;

import algorithme.ChainageAvant;
import gestionFichier.FileWR;


public class Test {
	public static void main (String[] args) throws IOException{

		FileWR f = new FileWR();
		
//		f.writeLine("Bonjour", "BF.txt");
//		f.writeLine("Bonjour", "BR.txt");
//		f.realLines("BF.txt");
//		f.realLines("BF.txt");
//		ArrayList<String> a = new ArrayList<String>();
//		a = f.readLines("BR.txt");
		
		
		ChainageAvant cav = new ChainageAvant(); 
		cav.verifiationChainageAvant();
//		for(String s : a){
//			System.out.println(s);
//		}
			
		
	}
}
