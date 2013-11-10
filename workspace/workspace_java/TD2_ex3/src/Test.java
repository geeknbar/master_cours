import java.net.*;
import java.rmi.*;
import java.io.*;
import java.util.*;
public class Test
{
	private static int ch;
	private static Scanner sc = new Scanner(System.in);
	private static ServerSocket ss;
	private static Socket sk;
	private static int port;
	private static String ipAdd = null;
	public static void main(String arg[])
	{

		port = (new Integer(arg[0]).intValue());
		try{
			ipAdd = (InetAddress.getLocalHost()).toString();
			System.out.println("\n**************************** **********************\n");
			System.out.println("IP \t: " + ipAdd);
			System.out.println("\nPort \t: " + port);
			do
			{
				choiceMenu();
				if(ch==1)
				{
					AcceptConnection();
				}
				else
					if(ch==2)
					{
						RequstConnection();
					}
					else
						if(ch==3)
						{
							SendMsg();
						}
						else
							if(ch==4)
							{
								DisplayMsg();
							}
			}
			while(ch!=5);
		}catch(Exception e)
		{}
	}
	private static void choiceMenu()
	{
		System.out.println("\n**************************** **********************");
		System.out.println("*********************| MENU |*********************");
		System.out.println("--------------------------------------------------\n");
		System.out.println("1] \t Accept Connection");
		System.out.println("2] \t Request for Connection");
		System.out.println("3] \t Send Message");
		System.out.println("4] \t Display Message");
		System.out.println("5] \t Exit");
		System.out.print("\nChoice A Option : ");
		ch=sc.nextInt();
	}
	private static void AcceptConnection()
	{
		int rePort = 0;
		try{
			System.out.print("Enter Port : ");
			rePort = sc.nextInt();
			ss = new ServerSocket(rePort);
			sk = ss.accept();
			System.out.println("Request Accepted");
		}catch(Exception e)
		{}
	}
	private static void RequstConnection()
	{
		String reIp = null;
		int rePort = 0;
		try{
			System.out.print("Enter IP : ");
			reIp=sc.next();
			System.out.print("Enter Port : ");
			rePort=sc.nextInt();
			sk = new Socket(reIp,rePort);
			System.out.println("Request Accepted");

		}catch(Exception e)
		{}
	}
	private static void SendMsg()
	{
		String msg = null;
		try{

			System.out.print("Enter Message : ");
			msg = sc.toString();
			ObjectOutputStream oos = new ObjectOutputStream(sk.getOutputStream());
			oos.writeObject(msg); 
		}catch(Exception e)
		{}
	}
	private static void DisplayMsg()
	{
		try{

			ObjectInputStream ois = new ObjectInputStream(sk.getInputStream());
			String msg = (String)ois.readObject();
			System.out.println("Messesage : " + msg); 

		}catch(Exception e)
		{}
	}
}
