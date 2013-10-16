/**
 * 
 */
package algorithme;

import gestionFichier.FileWR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Mickael
 *
 */
public class ChainageArriere 
{
	private ArrayList<String> BR;
	private ArrayList<String> Demandable;
	private ArrayList<String> BF;
	private ArrayList<String> But;
	private boolean inf;
	private boolean dec;
	private int nbinf;
	private String[] regleSplitTab;
	private ArrayList<String> regleSplitArray;
	private ArrayList<String> antecedents;
	private String regle;



	public ChainageArriere()
	{
		//initialisation des variables globales
		BR = new ArrayList<String>();
		BF = new ArrayList<String>();
		But = new ArrayList<String>();
		antecedents = new ArrayList<String>();
		inf = true;
		nbinf = 0;

		//charge but
		But.add("usure freins");
		//charge les fichiers BR et BF
		chargerBR();
		chargerBF();

	}
	
	

	//chainage avant en saturation
	public boolean verifiationChainageArriere(String but){
		boolean res;
		if(But.isEmpty()){
			res = true;
		}else{
			int i = 0;
			if(demontrerBut(But.get(i))){
				i++;
				res = verifiationChainageArriere(But.get(i));				
			}else{
				res = false;
			}
				
		}
		System.out.println("vCA : " + res);
		return res;
	}
	
	public boolean demontrerBut(String but){
		boolean res = true;
		
		if (BF.contains(but)){
			res = true;
		}else{
			res = false;
			ArrayList<String> regles = BR;
			while(!regles.isEmpty() && res == false ){
				
				chargerRegle(0);
				regles.remove(0);
				if (regle.equals(but)){
					res = verifiationChainageArriere(but);
				}		
			}
			
		}
		System.out.println("dB : " + res);
		return res;
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

	public void chargerBF(){
		try {
			FileWR fBF = new FileWR();
			BF = fBF.readLines("./src/doc/BF.txt");
		} catch (IOException e) {
			System.err.println("ERREUR : chargement du fichier BF");
			e.printStackTrace();
		}
	}

	public void chargerBR(){
		try {
			FileWR fBR = new FileWR();
			BR = fBR.readLines("./src/doc/BR.txt");
		} catch (IOException e) {
			System.err.println("ERREUR : chargement du fichier BR");
			e.printStackTrace();
		}
	}

	public boolean isInf() {
		return inf;
	}

	public void setInf(boolean inf) {
		this.inf = inf;
	}

	public boolean isDec() {
		return dec;
	}

	public void setDec(boolean dec) {
		this.dec = dec;
	}

	public int getNbinf() {
		return nbinf;
	}

	public void setNbinf(int nbinf) {
		this.nbinf = nbinf;
	}

	public ArrayList<String> getBR() {
		return BR;
	}

	public void setBR(ArrayList<String> bR) {
		BR = bR;
	}

	public ArrayList<String> getDemandable() {
		return Demandable;
	}

	public void setDemandable(ArrayList<String> demandable) {
		Demandable = demandable;
	}

	public ArrayList<String> getBF() {
		return BF;
	}

	public void setBF(ArrayList<String> bF) {
		BF = bF;
	}	
}

