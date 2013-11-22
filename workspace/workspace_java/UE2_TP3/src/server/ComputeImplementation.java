package server;
import java.math.BigInteger;
import java.rmi.*;
import java.rmi.server.*;

import common.ComputeInterface;
import common.InputData;
import common.Result;

public class ComputeImplementation  extends UnicastRemoteObject implements ComputeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComputeImplementation() throws RemoteException {
		super();
	}

	public BigInteger fibonacci(int n) throws RemoteException {
		BigInteger tab[] =new BigInteger[n+1];
		tab[0] = BigInteger.ZERO;
		tab[1] = BigInteger.ONE;
		
		for (int i = 2; i<=n; i++){
			tab[i] = tab[i-1];
			tab[i] = tab[i].add(tab[i-2]);
		}
		return tab[n];

	}

	public BigInteger factorial(int n) throws RemoteException{
		BigInteger resultat = BigInteger.valueOf(1);
		while(n>0){
			resultat = resultat.multiply(BigInteger.valueOf(n));
			n = n-1 ;
		}
		return resultat;

	}
	
	public void compute(InputData d) throws RemoteException{
		int data[];
		data = d.getTab();
		
		for (int i = 0; i < data.length; i++) {
			System.out.println("> "+ i + ": " + factorial(data[i]));
		}
	}
	
	public Result compute(int n) throws RemoteException{
		Result r = new Result();
		r.set(factorial(n), fibonacci(n));
		return r;
	}
}
