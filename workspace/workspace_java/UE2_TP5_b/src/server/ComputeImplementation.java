package server;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.*;
import java.rmi.server.*;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import common.ComputeInterface;
import common.MyJavaFileManager;
public class ComputeImplementation  extends UnicastRemoteObject implements ComputeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Object result;

	public ComputeImplementation() throws RemoteException {
		super();
	}


	public Object runCode(String code){
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		String program = "import java.io.*;"
				+ "public class Compute implements Runnable {"
				+ "   protected Object result; "
				+ "   public Compute() { } "
				+ "   public void run (){ "
				+ code
				+ "   }"
				+ "   public Object getResult() {"
				+ "     return result; "
				+ "   } "
				+ "}";

		Iterable<? extends JavaFileObject> fileObjects = getJavaSourceFromString("Compute", program);

		MyJavaFileManager fileManager = new MyJavaFileManager(compiler.getStandardFileManager(null, null, null));

		compiler.getTask(null, fileManager, null, null, null, fileObjects).call();

		ClassLoader loader = fileManager.getClassLoader();

		Class<?> clazz = null;
		try {
			clazz = Class.forName("Compute", true, loader);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Runnable r = Runnable.class.cast(t);

		r.run();

		// invoke method 
		Method methods[] = clazz.getMethods();
		for (int i=0; i<methods.length; ++i) {
			if (methods[i].getName().equals("getResult")) {

				try {
					result = methods[i].invoke(t, new Object[] {});
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	     
			}
		}
		
		return result;
	}


	private Iterable<? extends JavaFileObject> getJavaSourceFromString(String string, String program) {
		// TODO Auto-generated method stub
		return null;
	}
}
