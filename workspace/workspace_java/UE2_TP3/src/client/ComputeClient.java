package client;
import java.rmi.*;

import common.ComputeInterface;
import common.InputData;
import common.Result;

public class ComputeClient {

	public static void main(String args[]) {
		try {

			//		      String url = "rmi://localhost:3009/Compute";
			String url = "rmi://localhost:3001/Compute";
			ComputeInterface compute = (ComputeInterface)
					Naming.lookup(url);

			System.out.println("factorial : " + compute.factorial(24));
			System.out.println("fibonacci : " +compute.fibonacci(60));

			int d1[] = {4, 7, 12}; 
			InputData input = new InputData(d1);
			compute.compute(input);
			
			Result r = compute.compute(20);
			System.err.println("fac(20)" + r.getData()[0]);
			System.err.println("fib(20)" + r.getData()[1]);
		} catch(Exception e) {
			System.err.println("Client: " + e);
		}
	}
}
