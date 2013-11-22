package common;

import java.io.Serializable;
import java.util.Arrays;

public class InputData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tab[];
	

	public InputData(int t[]){
		
		tab = new int [ t.length];
		tab = Arrays.copyOf(t, t.length);
	}
	
	public int[] getTab() {
		return tab;
	}
	
	
}
