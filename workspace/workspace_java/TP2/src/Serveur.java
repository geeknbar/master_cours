import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

class Serveur {
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

				while(true){

					String input = in.readLine();
					System.out.println("client send> " + input);
					StringTokenizer token = new StringTokenizer(input, ":");
					String clientName = token.nextToken();
					String message = token.nextToken();

					if (message == "Quit"){
						break;
					}else{
						out.println(message.toUpperCase());
					}
				}
				out.close();
				in.close();
			}
			//			out.close();
			//			in.close();
			//			client.close();
			//			server.close();

		} catch(Exception e) {
			System.err.println("Serveur" + e);
		}
	}
}