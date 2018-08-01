package fullduplexclientside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

class receiver extends Thread
{
	Socket s1 = null;
	String name;
	public receiver(Socket s, String x) 
	{
		s1=s;	
		name = x;
	}

	public void run()
	{
		try
		{
			DataInputStream dis = new DataInputStream(s1.getInputStream());
			while(true)
			{
				String str = dis.readUTF();
				System.out.println("                             "+name+": "+str);
			}
		}
		catch(Exception e) {System.out.println("Client Down"); }
	}
}


public class duplexclient {

	private static Scanner sc;
	private static Socket s;

	public static void main(String[] args) 
	{
		sc = new Scanner(System.in);
		String x;
		try
		{
			System.out.println("Enter the name of the person to chat with: ");
			String name = sc.next();
			System.out.println("Enter the ip address of the server system: ");
			String ip = sc.next();
			System.out.println("Enter port number: ");
			int port = sc.nextInt();
			s = new Socket(ip,port);
			receiver thd1 = new receiver(s,name);
			thd1.start();
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			while(true)
			{
				System.out.println("You: ");
				x = sc.nextLine();
				dos.writeUTF(x);
			}
		}
		catch(Exception e) { }
	}

}
