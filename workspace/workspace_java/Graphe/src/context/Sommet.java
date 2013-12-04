package context;

/**
 * @author Mickael
 *
 */
public class Sommet
{

	private int value;
	
	public Sommet(int value)
	{
		this.value= value;
	}
	
	public Sommet(Sommet s)
	{
		this.value= s.getValue();
	}

	public int getValue() 
	{
		return value;
	}

	public void setValue(int value) 
	{
		this.value = value;
	}

	@Override
	public String toString() 
	{
		return "Sommet [value=" + value + "]";
	}
}
