/**
 * 
 */
package algorithme;

import gestionFichier.FileWR;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mickael
 *
 */
public class ChainageArriere 
{
	private ArrayList<String> BR;
	private ArrayList<String> Demandable;
	private ArrayList<String> BF;
	private boolean inf;
	private boolean dec;
	private int nbinf;



	public ChainageArriere()
	{
		//initialisation des variables globales
		BR = new ArrayList<String>();
		BF=  new ArrayList<String>();
		inf = true;
		nbinf = 0;

		//charge les fichiers BR et BF
		chargerBR();
		chargerBF();

	}

	//chainage avant en saturation
	public void verifiationChainageArriere()
	{
		System.out.println("START");

		System.out.println("END");
	}

	public void chargerBF(){
		try {
			FileWR fBF = new FileWR();
			BF = fBF.readLines("BF.txt");
		} catch (IOException e) {
			System.err.println("ERREUR : chargement du fichier BF");
			e.printStackTrace();
		}
	}

	public void chargerBR(){
		try {
			FileWR fBR = new FileWR();
			BF = fBR.readLines("BR.txt");
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

