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
public class ChainageAvant 
{
	private ArrayList<String> BR = new ArrayList<String>();
	private ArrayList<String> BF=  new ArrayList<String>();
	private boolean inf = true;

	public ChainageAvant()
	{
		//charge les fichiers BR et BF
		chargerBR();
		chargerBF();
	}

	//chainage avant en saturation
	public void verifiationChainageAvant()
	{
		System.out.println("START");
		while (inf)	{
			inf = false;
			BR.add("R1,demarre,passage vitesse impossible,boite casse");
			BR.add("R2,passage vitesse impossible,roule pas");
			BR.add("R3,demarre,roule,nuit,feux grille");
			BR.add("R4,acceleration,passage vitesse,roule");
			BR.add("R5,nuit,non vision route");
			BR.add("R6,demarre,roule,bruit suspect,usure freins");
			for (int i = 0; i < BR.size(); i++){

				//on charge la regle en la splitant dans un tableau
				String[] defRegle = BR.get(i).split(",");

				//on converti la regle en arraylist
				ArrayList<String> definitionRegle = new ArrayList(Arrays.asList(defRegle));

				//on sort le resulat de la règle, ex: A,B => C , ici on prend C
				String regle = definitionRegle.get(definitionRegle.size()-1).toString();

				//on fait l'array des antecedents
				ArrayList<String> antecedents = new ArrayList<String>();
				antecedents = definitionRegle;
				antecedents.remove(0);
				antecedents.remove(definitionRegle.size()-1);
				//affichage de la regles et des antecedents
				System.out.print("regle:" + regle + "antecedent ");
				for(String s : antecedents){
					System.out.print(s + " ,");
				}
				System.out.println("");
				//on parcours la base de fait pour voir si les antecedents sont presents
				System.out.print("BF :");
				for(String s : BF){
					System.out.print(s + " ,");
				}
				System.out.println("");

				boolean verif = true;
				for (int k = 0; k < antecedents.size(); k++) {
					if (!BF.contains(antecedents.get(k))){
						verif = false;
					}
				}
				
				if(verif){
					System.out.println("Regle : " + regle + " validée");
				}
			}
		}
		System.out.println("END");
	}

	public void chargerBF(){
		try {
			FileWR fBF = new FileWR();
			BF = fBF.readLines("BF.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void chargerBR(){
		try {
			FileWR fBR = new FileWR();
			BF = fBR.readLines("BR.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
