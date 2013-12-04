package ig;

import java.io.IOException;

import algorithme.AlgoGlouton;
import fileRW.ReadFile;

/**
 * @author Mickael
 *
 */
public class MainWindow
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ReadFile readFile = new ReadFile();
		try 
		{
			
//			readFile.readLines("./src/doc/simple4.txt");
//			readFile.readLines("./src/doc/graphe_125.txt");
//			readFile.readLines("./src/doc/graphe_250.txt");
//			readFile.readLines("./src/doc/graphe_500.txt");
//			readFile.readLines("./src/doc/graphe_500_5.txt");
//			readFile.readLines("./src/doc/graphe_2000.txt");
//			readFile.readLines("./src/doc/brock200_2.txt");
//			readFile.readLines("./src/doc/DSJC500_5.txt");
//			readFile.readLines("./src/doc/gen400_p0.9_75.txt");//47/75
//			readFile.readLines("./src/doc/hamming8-4.txt");//resultat parfait algo2
//			readFile.readLines("./src/doc/keller4.txt");//resultat parfait algo2
//			readFile.readLines("./src/doc/MANN_a27.txt");//125/126 algo2
//			readFile.readLines("./src/doc/p_hat300-1.tkt");//resultat parfait algo2
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.err.println(readFile.getGraphe().toString());

		AlgoGlouton a = new AlgoGlouton(readFile);
		a.cliqueMaximumGlouton();
		
	}

}
