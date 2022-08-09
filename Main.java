import java.util.Scanner;

public class Main {
	
		
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		IPAddress ipAddress = new IPAddress();
		
		System.out.print("Enter IP Address : ");
		if(ipAddress.convertToIp(sc.next())) {
			
			System.out.print("Enter Number of Subnets :");
			ipAddress.setSubNetCount(sc.nextInt());
			System.out.println("Class for Given IP Address : "+ipAddress.getClas());
			short ip[] = ipAddress.getDefaultSubnetMask();
			System.out.print("Default Subnet Mask : ");
			ipAddress.printIP(ip);
			System.out.println("\nNo of Bits to Borrow from NetId : "+ipAddress.getBitsRequired());
			String newSubnetM = ipAddress.getNewSubnetMask();
			System.out.println("New Subnet Mask : "+newSubnetM);
			ip = ipAddress.ipToBytes(newSubnetM);
			ipAddress.printIP(ip);
			
		}
		else {
			System.out.println("Invalid IP Adress Try Again..!");
		}
	}
}
