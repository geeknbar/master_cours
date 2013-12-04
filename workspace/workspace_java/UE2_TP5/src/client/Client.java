package client;

import java.net.*;
import java.io.*;

class Client {
	public static void main(String args[]) throws IOException {
		PrintWriter out = null;
		BufferedReader in = null;
		Socket s = null;

		String name = "Lulu";
		try {
			s = new Socket("localhost", 30970);
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					s.getInputStream())
					);

			String message = "String s=\"\";"
					+ "  try { " 
					+ "int tab[] = new int[40];"  
					+ "tab[0] = 0;  "
					+ "tab[1] = 1;  "
					+ "int n = parameters;  "
					+ "for (int i=2; i<=n; ++i) {   "
					+ "  tab[i] = tab[i-1] + tab[i-2];  "
					+ "}  "
					+ "result = (Object) tab[n];  "    
					+ "} catch(Exception e) {  "
					+ "e.printStackTrace();} ;";
			out.println(message+":10");

			String mS = in.readLine();
			System.out.println("from server> " + mS);
			out.println(name + ":Quit");


		} catch(Exception e) {
			System.err.println("Client: "+e);
		}finally{
			out.close();
			in.close();
			s.close();
		}
	}
}
