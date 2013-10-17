package gestionFichier;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
public class FileWR {

	private Path pathSource;
	private Path pathCible;
	private List<String> lignes = new ArrayList<String>();

	public FileWR(){

	}

	public ArrayList<String> readLines(String pathS) throws IOException{
		setPathSource(pathS);
		ArrayList<String> array = new ArrayList<String>();

		List<String> lignes =  Files.readAllLines(pathSource, StandardCharsets.UTF_8);  
		for (String ligne : lignes){
			array.add(ligne);
		}

		return array;
	}

	public void writeLine(String s, String pathC) throws IOException{
		setPathCible(pathC);
		ArrayList<String> fichier = readLines(pathC);
		if (!fichier.contains(s)){
			fichier.add(s);
		}
		Files.write(pathCible, fichier, Charset.defaultCharset());

	}

	public void setPathCible(String pathC){
		pathCible = Paths.get(pathC);
	}

	public void setPathSource(String pathS){
		pathSource = Paths.get(pathS);
	}
}
