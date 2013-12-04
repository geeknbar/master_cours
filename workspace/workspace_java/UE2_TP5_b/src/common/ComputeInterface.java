package common;
import java.rmi.*;
public interface ComputeInterface  extends Remote{
	public final static int RMI_PORT = 3001;
	
	public Object runCode(String code)throws RemoteException;


}
