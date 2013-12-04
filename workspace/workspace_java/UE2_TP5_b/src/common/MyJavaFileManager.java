package common;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;

public class MyJavaFileManager implements JavaFileManager {

	public MyJavaFileManager(StandardJavaFileManager standardFileManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int isSupportedOption(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClassLoader getClassLoader(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileObject getFileForInput(Location location, String packageName,
			String relativeName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileObject getFileForOutput(Location location, String packageName,
			String relativeName, FileObject sibling) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaFileObject getJavaFileForInput(Location location,
			String className, Kind kind) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaFileObject getJavaFileForOutput(Location location,
			String className, Kind kind, FileObject sibling) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean handleOption(String current, Iterator<String> remaining) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasLocation(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String inferBinaryName(Location location, JavaFileObject file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSameFile(FileObject a, FileObject b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<JavaFileObject> list(Location location, String packageName,
			Set<Kind> kinds, boolean recurse) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

}
