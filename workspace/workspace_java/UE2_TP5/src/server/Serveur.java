package server;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Collections;
import java.util.StringTokenizer;
import java.io.*;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import server.MyJavaFileManager;

class Serveur {
	private static Object result;

	public static void main(String args[]) {
		PrintWriter out = null;
		BufferedReader in = null;
		Socket client = null;
		ServerSocket server = null;
		try {
			server = new ServerSocket(30970);

			//il manque de stocker la class puis qu'un deuxième client utilise cette class pour un tableau de paramètre.
			while(true){
				client = server.accept();
				out = new PrintWriter(client.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));

				//				while(true){


				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				String input = in.readLine();
				System.out.println("client send> " + input);
				StringTokenizer token = new StringTokenizer(input, ":");
				String code = token.nextToken();
				String param = token.nextToken();

				String program = "import java.io.*;"
						+ "public class Compute implements Runnable {"
						+ "   protected Object result; "
						+ "   protected int parameters;"
						+ "   public Compute() { } "
						+ "   public void run (){ "
						+ code
						+ "   }"
						+ "   public Object getResult() {"
						+ "     return result; "
						+ "   } "
						+ "   public void setParameters(int p) {"
						+ "       parameters = p;"
						+ "  }"
						+ "}";

				Iterable<? extends JavaFileObject> fileObjects = getJavaSourceFromString(
						"Compute", program);

				MyJavaFileManager fileManager = new MyJavaFileManager(compiler
						.getStandardFileManager(null, null, null));

				compiler.getTask(null, fileManager, null, null, null, fileObjects)
				.call();

				ClassLoader loader = fileManager.getClassLoader();

				Class<?> clazz = Class.forName("Compute", true, loader);

				Object t = clazz.newInstance();
				Runnable r = Runnable.class.cast(t);

				Method methods[] = clazz.getMethods();
				
				for (int i=0; i<methods.length; ++i) {
					if (methods[i].getName().equals("setParameters")) {
						methods[i].invoke(t, Integer.valueOf(param)); 
					}
				} 
				r.run();

				// invoke method 
				for (int i=0; i<methods.length; ++i) {
					if (methods[i].getName().equals("getResult")) {
						result = methods[i].invoke(t, new Object[] {}); 
					}
				} 

				if (program == "Quit"){
					break;
				}else{
					out.println(result.toString());
				}
			}
			out.close();
			in.close();
			//			}

		} catch(Exception e) {
			System.err.println("Serveur" + e);
		}
	}

	public static Iterable<JavaSourceFromString> getJavaSourceFromString(
			String fileName, String code) {
		return Collections.singletonList(new JavaSourceFromString(fileName,
				code));
	}
}