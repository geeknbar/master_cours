package tchat.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
	ServerSocket server;
	ArrayList<ServerConnection> connection;

	public Server(){
		try {
			server = new ServerSocket(30970);
			Socket client;
			connection = new ArrayList<ServerConnection>();
			while (true) {
				client = server.accept();
				if(connection.size() <3){
					ServerConnection sc= (new ServerConnection(this, client));	
					connection.add(sc);
					sc.start();
				}else{
					System.err.println("No more accepted");
				}
			}
			//server.close();
		} catch(Exception e) {
			System.err.println(e);
		}
	}

	public void send(String clientName, String msg){
		System.out.println(msg);
		for (ServerConnection c : connection) {
			if(c.getClientName() != clientName){
				c.send("sendby>"+ clientName + " : "+msg);
			}

		}
	}

	public static void main(String arg[]) {
		try {
			Server s = new Server();
		} catch(Exception e) {
			System.err.println(e);
		}

	}

}