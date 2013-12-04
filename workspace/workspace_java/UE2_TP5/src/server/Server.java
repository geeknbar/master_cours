package server;

import java.util.Collections;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class Server {
	 	 
		public static Iterable<JavaSourceFromString> getJavaSourceFromString(
				String fileName, String code) {
			return Collections.singletonList(new JavaSourceFromString(fileName,
					code));
		}
	 
		public static void main(String[] args) throws Exception {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			String program = "public class Test implements Runnable {"
					+ "   public void run (){"
					+ "      System.out.println (\"Hello, World\");"
					+ "   }"
					+ "}";
	 
			Iterable<? extends JavaFileObject> fileObjects = getJavaSourceFromString(
					"Test", program);
	 
			MyJavaFileManager fileManager = new MyJavaFileManager(compiler
					.getStandardFileManager(null, null, null));
	 
			compiler.getTask(null, fileManager, null, null, null, fileObjects)
					.call();
	 
			ClassLoader loader = fileManager.getClassLoader();
	 
			Class<?> clazz = Class.forName("Test", true, loader);
	 
			Runnable r = Runnable.class.cast(clazz.newInstance());
			r.run();
		}
}
