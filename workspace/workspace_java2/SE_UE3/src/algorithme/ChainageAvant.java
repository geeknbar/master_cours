package algorithme;

import gestionFichier.FileWR;
import interfaceGraphique.MainWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Dorian C., Mickael F.
 *
 */
public class ChainageAvant 
{
	private ArrayList<String> BR;
	private ArrayList<String> BF;
	private boolean inf;
	private String[] regleSplitTab;
	private ArrayList<String> regleSplitArray;
	private ArrayList<String> antecedents;
	private String regle;
	private FileWR fileRW;
	private String result;
	private boolean chainageFin;
	public String minimumResult;

	public ChainageAvant()
	{
		//initialisation des variables globales
		result="";
		BR = new ArrayList<String>();
		BF =  new ArrayList<String>();
		inf = true;
		antecedents = new ArrayList<String>();
		fileRW=new FileWR();
		minimumResult="";

		//charge les fichiers BR et BF
		chargerBR();
		chargerBF();
	}

	//chainage avant profondeur
	public void verifiationChainageAvant()
	{
		System.out.println("START");
		while (inf)	{
			inf = false;
			for (int i = 0; i < BR.size(); i++){

				chargerRegle(i);	
				chargeAntecedent();

				
				

				boolean verif = true;
				for (int k = 0; k < antecedents.size(); k++) {
					if (!BF.contains(antecedents.get(k))){
						verif = false;
					}
				}
				chainageFin = true;
				if(verif){
					remplirBF(regle);
					affichageRegle();
					result+="Regle : " + BR.get(i) + " validee\n\n";
					System.out.println("Regle : " + regle + " validee");
					chainageFin = false;
					BR.remove(i);
				}
			}
		}
		System.out.println("END");
	}

	//chainage avant profondeur saturation
	public void profondeurSaturation(){
		int iter = 0;
		minimumResult+="BF de depart : ";
		affichageminiBF();
		while(!chainageFin){
			affichageBF();
			result+=("Tour : "+ (iter+1) + "\n");
			verifiationChainageAvant();
			chargerBF();
			inf=true;
			iter++;
			result+=("==================\n");
		}
		result+=("Fin du chainage avant en saturation \n");
		affichageBF();
		minimumResult+="BF apres saturation : ";
		affichageminiBF();
	}
	
	//chainage avant profondeur but
	public void profondeurBut(String but){
		int iter = 0;
		
		while(!chainageFin && !BF.contains(but)){
			System.out.println("Tour : "+ (iter+1));
			verifiationChainageAvant();
			chargerBF();
			inf=true;
			iter++;
		}
		if(BF.contains(but)){
			result+="But : " + but + " trouve\n";
			minimumResult+="But : " + but + " trouve\n";
			System.out.println("But : " + but + " trouve");
		}else{
			System.out.println("But : " + but + " non trouve");
			result+="But : " + but + " non trouve\n";
			minimumResult+="But : " + but + " non trouve\n";
		}
		affichageBF();
	}

	public void remplirBF(String but){
		try {
			fileRW.writeLine(but,"./src/doc/BF.txt");
		} catch (IOException e) {
			System.err.println("ERREUR : dans l'ecriture du fichier BF");
			e.printStackTrace();
		}
	}

	public void chargerBF(){
		try {
			//"./src/doc/BF.txt"
//			BF = fileRW.readLines("./src/doc/BF.txt");
						BF = fileRW.readLines(MainWindow.getFichierBF());
		} catch (IOException e) {
			result="ERREUR : chargement du fichier BF\n";
			System.err.println("ERREUR : chargement du fichier BF");
			e.printStackTrace();
		}
	}

	public void chargerBR(){
		try {
			//"./src/doc/BR.txt"
//			BR = fileRW.readLines("./src/doc/BR.txt");
						BR = fileRW.readLines(MainWindow.getFichierBR());
		} catch (IOException e) {
			result="ERREUR : chargement du fichier BR\n";
			System.err.println("ERREUR : chargement du fichier BR");
			e.printStackTrace();
		}
	}

	public void chargerRegle(int i){
		//on charge la regle en la splitant dans un tableau
		regleSplitTab = BR.get(i).split(",");

		//on converti la regle en arraylist
		regleSplitArray = new ArrayList<String>(Arrays.asList(regleSplitTab));

		//on sort le resulat de la règle, ex: A,B => C , ici on prend C
		regle = regleSplitArray.get(regleSplitArray.size()-1).toString();

	}

	public void chargeAntecedent(){
		//on fait l'array des antecedents
		antecedents = regleSplitArray;
		antecedents.remove(0);
		antecedents.remove(regleSplitArray.size()-1);
	}

	public void affichageRegle(){
		//affichage de la regles et des antecedents
		System.out.print("regle:" + regle + " antecedent ");
		result+="La regle " + " a pour but " + regle + " antecedent : ";
		for(String s : antecedents){
			result+=s + " ";
			System.out.print(s + " ,");
		}
		result+="\n";
		System.out.println("");
	}

	public void affichageBF(){
		//on parcours la base de fait pour voir si les antecedents sont presents
		System.out.print("BF :");
		result+="BF :";
		for(String s : BF){
			System.out.print(s + " ,");
			result+=s + " ";
		}
		System.out.println("");
		result+="\n";
	}
	
	public void affichageminiBF(){
		//on parcours la base de fait pour voir si les antecedents sont presents
		for(String s : BF){
			minimumResult+=s + " ";
		}
		System.out.println("");
		minimumResult+="\n";
	}
	
	public String affichageAreaBR(){
		//on parcours la base de fait pour voir si les antecedents sont presents
		String areaBF ="";
		for(String s : BR){
			areaBF+=s + " \n";
		}
		System.out.println("");
		return areaBF+="\n";
	}

	public ArrayList<String> getBR() {
		return BR;
	}

	public void setBR(ArrayList<String> bR) {
		BR = bR;
	}

	public ArrayList<String> getBF() {
		return BF;
	}

	public void setBF(ArrayList<String> bF) {
		BF = bF;
	}

	public boolean isInf() {
		return inf;
	}

	public void setInf(boolean inf) {
		this.inf = inf;
	}

	public String[] getRegleSplitTab() {
		return regleSplitTab;
	}

	public void setRegleSplitTab(String[] regleSplitTab) {
		this.regleSplitTab = regleSplitTab;
	}

	public ArrayList<String> getRegleSplitArray() {
		return regleSplitArray;
	}

	public void setRegleSplitArray(ArrayList<String> regleSplitArray) {
		this.regleSplitArray = regleSplitArray;
	}

	public ArrayList<String> getAntecedents() {
		return antecedents;
	}

	public void setAntecedents(ArrayList<String> antecedents) {
		this.antecedents = antecedents;
	}

	public String getRegle() {
		return regle;
	}

	public void setRegle(String regle) {
		this.regle = regle;
	}

	public FileWR getFileRW() {
		return fileRW;
	}

	public void setFileRW(FileWR fileRW) {
		this.fileRW = fileRW;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getMinimumResult() {
		return minimumResult;
	}

	public void setMinimumResult(String minimumResult) {
		this.minimumResult = minimumResult;
	}
}
