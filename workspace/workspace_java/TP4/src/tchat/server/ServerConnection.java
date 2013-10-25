package tchat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection extends Thread{

	protected String clientName;
	protected Socket client;
	protected Server srv;
	PrintWriter out = null;
	BufferedReader in = null;
	
	public ServerConnection(Server srv, Socket s){
		clientName = "";
		client = s;
		this.srv =srv;
	}
	
	public void run(){
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String input = in.readLine();
			clientName = input;
			while(true){
				input = in.readLine();
				srv.send(clientName, input);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getClientName() {
		return clientName;
	}

	public void send(String msg) {
		out.println(msg);
	}
}
