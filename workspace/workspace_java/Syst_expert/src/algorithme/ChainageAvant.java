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
public class ChainageAvant 
{
	private ArrayList<String> BR;
	private ArrayList<String> Demandable;
	private ArrayList<String> BF;
	private ArrayList<String> VF;
	private ArrayList<String> VA;
	private ArrayList<String> VC;
	private boolean inf;
	private boolean dec;
	private int nbinf = 0;
	private FileWR f = new FileWR();



	public ChainageAvant()
	{
		// TODO Auto-generated constructor stub
		chargerBF();
		chargerBR();
	}

	public void verifiationChainageAvant()
	{
		//TODO M C'est quoi??!!
		
		String m = new String("");
		String r = new String("");
		
		while (inf)
		{
			inf = false;
			for (int i = 0; i < BR.size(); i++)
			{
				dec = true;
				for (int j = 0; j < VA.size(); j++)
				{
					while (dec)
					{
					if(BF.contains(m));
//						{
//							dec = false;
//						}
					}
				}
				if (dec){
					
				}
				
			}
		}
	}
	
	public void chargerBF(){
		try {
			BF = f.realLines("BF.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void chargerBR(){
		try {
			BF = f.realLines("BR.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public ArrayList<String> getVF() {
		return VF;
	}

	public void setVF(ArrayList<String> vF) {
		VF = vF;
	}

	public ArrayList<String> getVA() {
		return VA;
	}

	public void setVA(ArrayList<String> vA) {
		VA = vA;
	}

	public ArrayList<String> getVC() {
		return VC;
	}

	public void setVC(ArrayList<String> vC) {
		VC = vC;
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
