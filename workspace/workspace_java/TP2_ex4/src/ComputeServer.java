import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

class ComputeServer {
	public static void main(String args[]) {
		PrintWriter out = null;
		BufferedReader in = null;
		Socket client = null;
		ServerSocket server = null;
		try {
			server = new ServerSocket(30970);

			while(true){
				client = server.accept();
				out = new PrintWriter(client.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));

				String input = in.readLine();
				System.out.println("from client> " + input);
				Runtime runtime = Runtime.getRuntime();
				
				String[] agrs = {"/usr/bin/dc","-e", input};
				
				
				final Process process = runtime.exec(args);
				
				BufferedReader reader = new BufferedReader(in);
				String line ="";
				
				
				out.close();
				in.close();
			}

		} catch(Exception e) {
			System.err.println("Serveur" + e);
		}
	}
}