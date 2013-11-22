package common;
import java.math.BigInteger;
import java.rmi.*;
public interface ComputeInterface  extends Remote{
	public final static int RMI_PORT = 3001;

	public BigInteger fibonacci(int n) throws RemoteException;

	public BigInteger factorial(int n) throws RemoteException;
	
	public void compute(InputData d) throws RemoteException;
	
	public Result compute(int n) throws RemoteException;
}
