package client;
import java.rmi.*;

import common.ComputeInterface;

public class ComputeClient {

	public static void main(String args[]) {
		try {

			//String url = "rmi://localhost:3009/Compute";
			String url = "rmi://localhost:3001/Compute";
			ComputeInterface compute = (ComputeInterface)Naming.lookup(url);
			String code = "";
			compute.runCode(code);
			
			
		} catch(Exception e) {
			System.err.println("Client: " + e);
		}
	}
}
