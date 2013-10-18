import java.net.*;
import java.io.*;
 
class MultiThreadSocketServer {
  public static void main(String args[]) {
         
    try {
      ServerSocket server = new ServerSocket(30970);
 
      while (true) {
        Socket client = server.accept();
 
        ClientDialogThread t = new ClientDialogThread(client);
        t.start();
      }
     
      //server.close();
     
    } catch(Exception e) {
      System.err.println(e);
    }
  }
}