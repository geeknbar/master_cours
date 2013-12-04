package common;

import java.io.Serializable;
import java.math.BigInteger;

public class Result implements Serializable{

	private BigInteger data [];

	public Result(){
		data = new BigInteger[2];
		data[0]= BigInteger.ZERO;
		data[1]= BigInteger.ONE;


	}
	
	public void set(BigInteger fact, BigInteger fib){
		data[0] = fact;
		data[1] = fib;
	}

	public BigInteger[] getData() {
		// TODO Auto-generated method stub
		return data;
	}
}
