import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class ClientDialogThread extends Thread {

	Socket client;
	PrintWriter out = null;
	BufferedReader in = null;

	public ClientDialogThread(Socket s) {
		client = s;
	}

	public void run() {
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));   

			while (true) {
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

			in.close();
			out.close();
			client.close();

		} catch(Exception e) {
			System.err.println(e);
		}
	}
}