package tchat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection extends Thread{

	PrintWriter out = null;
	BufferedReader in = null;
	Socket socket;
	ClientGUI client;

	public ClientConnection(ClientGUI c,Socket s, String name){
		client = c;
		socket = s;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out.println(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run(){

		try {
			while(true){
				String input = in.readLine();
				client.addMessage(input);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(String msg){
		System.out.println(msg);
		out.println(msg);
	}
}
