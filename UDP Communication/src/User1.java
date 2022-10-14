import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class User1{
	
	final int sendPort = 6701;
	final int recievePort = 6702;
	DatagramSocket sendDataSocket;
	DatagramSocket recieverDataSocket;
	InetAddress inetAddress;
	DatagramPacket dataPacket;
	String myMsg;
	String recievedMsg;
	
	public User1() {
		Scanner sc = new Scanner(System.in);
		
		try {
			sendDataSocket = new DatagramSocket();
			recieverDataSocket = new DatagramSocket(recievePort);
			inetAddress = InetAddress.getByName("localhost");
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n\tChatting Server Started ..");
		System.out.println("\tType bye to Stop Server ");
	
		System.out.println("\n\tUser2\n");
	
		while(true) {
			System.out.print("\t\t\t\t\t");
			myMsg = sc.nextLine();
			
			dataPacket = new DatagramPacket(myMsg.getBytes(),myMsg.length(),inetAddress,sendPort);
			
			try {
				sendDataSocket.send(dataPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(myMsg.equalsIgnoreCase("bye")) {
				break;
			}
			
			
			DatagramPacket dp = new DatagramPacket(new byte[512], 512);	
			try {
				recieverDataSocket.receive(dp);
				recievedMsg = new String(dp.getData(),0,dp.getLength());
				System.out.println("\t"+recievedMsg);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(recievedMsg.equalsIgnoreCase("bye")) {
				break;
			}
		}
		System.out.println("\nTerminated!!");
		sendDataSocket.close();
		recieverDataSocket.close();
		
	}
	
	public static void main(String Args[]) {
		new User1();
	}
}