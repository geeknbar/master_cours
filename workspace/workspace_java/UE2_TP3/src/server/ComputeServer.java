package server;
import java.rmi.*;

import common.ComputeInterface;
public class ComputeServer {
	public ComputeServer() {
		try {

			java.rmi.registry.LocateRegistry.createRegistry(ComputeInterface.RMI_PORT);
			ComputeImplementation impl = new ComputeImplementation();
			String url = "rmi://localhost:3001/Compute";
			Naming.rebind(url, impl);

		} catch(Exception e) {
			System.err.println(getClass().getName() + ": " + e);
		}
	}

	public static void main(String args[]) {
		new ComputeServer();
	}
}
