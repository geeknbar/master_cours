package gestionFichier;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
public class FileWR {

	private Path pathSource;
	private Path pathCible;
	private List<String> lignes;

	public FileWR(){

	}

	public void realLines() throws IOException{
		List<String> lignes =  Files.readAllLines(  
				FileSystems.getDefault().getPath("monfichier.txt"), StandardCharsets.UTF_8);  
		for (String ligne : lignes)
			System.out.println(ligne);
	}

	public void writeLine(String s) throws IOException{
		pathCible = Paths.get("c:/java/cible.bin");
		lignes.add(s);
		Files.write(pathCible, lignes, Charset.defaultCharset());

	}
}
