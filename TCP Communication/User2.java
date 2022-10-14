import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class User2 {
	
	Socket socket;
	BufferedReader reader;
	PrintWriter out;
	OutputStreamWriter outStream;
	String recievedMsg;
	String myMsg;
	
	public User2() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\tChatting Server Started :");
		
		try {
			socket = new Socket("192.168.208.61",8601);
			outStream = new OutputStreamWriter(socket.getOutputStream());
			System.out.println("\tConnected With Kunal");
			out = new PrintWriter(outStream);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n\n\t"+"--Kunal--"+"\n");
		
		while(true) {
			System.out.print("\t\t\t\t\t");
			myMsg = sc.nextLine();
			out.println("\t"+myMsg);
			if(myMsg.trim().equalsIgnoreCase("bye"))
				break;
			try {
				out.flush();
				recievedMsg = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(recievedMsg == null) {
				System.out.println("\t"+"bye");
				break;
			}
			System.out.println(recievedMsg);
			
		}	
		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\n\tDisconnected!");	
	}
	
	public static void main(String Agrs[]) throws Exception{
		
		new User2();

	}	
}
