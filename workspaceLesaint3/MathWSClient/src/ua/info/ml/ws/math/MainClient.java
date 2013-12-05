package ua.info.ml.ws.math;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import ua.info.ml.ws.math.MathServiceLocator;
public class MainClient {
	public static void main(String[] args) {
		System.out.println("Client avec utilisation de proxy");
		MathServiceLocator m = new MathServiceLocator();
		try {
			ua.info.ml.ws.math.Math service = m.getMath();
		System.out.println(service.add(90,10));
		} catch(ServiceException e) {
		e.printStackTrace();
		} catch (RemoteException e) {
		e.printStackTrace();
		}
		}

}
