import java.net.*;
import java.io.*;

class ComputeClient {
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

			System.out.println("computation> ");
			String input;
			
			input = "dc -e '4 5 * p'";
			out.println(input);
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
