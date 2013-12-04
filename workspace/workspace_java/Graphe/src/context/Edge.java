package context;

import java.util.ArrayList;

/**
 * @author Mickael
 *
 */
public class Edge 
{
	private ArrayList<Sommet> listSommet;
	private final static int SOMMET_1=0;
	private final static int SOMMET_2=1;
	
	public Edge(Sommet s1, Sommet s2) 
	{
		listSommet = new ArrayList<Sommet>();
		listSommet.add(s1);
		listSommet.add(s2);
	}

	public boolean isInEdges(Sommet s1, Sommet s2)
	{
		if(listSommet.get(Edge.SOMMET_1).getValue() == s1.getValue() && listSommet.get(Edge.SOMMET_2).getValue()== s2.getValue())
		{
			return true;
		}
		
		return false;
	}
	

	public ArrayList<Sommet> getListSommet() 
	{
		return listSommet;
	}

	public void setListSommet(ArrayList<Sommet> listSommet) 
	{
		this.listSommet = listSommet;
	}
	
	public Sommet getSommet(int index) 
	{
		return listSommet.get(index);
	}


	public static int getSommet1() 
	{
		return SOMMET_1;
	}

	public static int getSommet2() 
	{
		return SOMMET_2;
	}


	@Override
	public String toString() 
	{
		return "Edge [Sommet 1 = " + this.getSommet(Edge.SOMMET_1) + "Sommet 2 = " + this.getSommet(Edge.SOMMET_2)+ "]";
	}
}
