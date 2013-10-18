import java.net.*;
import java.io.*;

class Client {
	public static void main(String args[]) throws IOException {
		PrintWriter out = null;
		BufferedReader in = null;
		Socket s = null;

		String name = "Dorian";
		try {
			s = new Socket("localhost", 30970);
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					s.getInputStream())
					);

			for (int i = 0; i < 5; i++) {
				out.println(name + ":hello server");
				Thread.sleep(2000);
				String mS = in.readLine();
				System.out.println("from server> " + mS);
			}
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
