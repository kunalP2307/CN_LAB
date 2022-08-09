
public class IPAddress {
	
	public static short endAdd[] = {127,191,223};
	private short[] ip;
	private int subNetCount;
	
	
	public void setSubNetCount(int count) {
		this.subNetCount = count;
	}
	public boolean convertToIp(String strIp) {
		
		String ipPattern = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
	    
		if(strIp.matches(ipPattern)) {
			String bytes[] = strIp.split("[.]");
			ip = new short[4];
			for(int i=0; i<4; i++) 
				ip[i] = Short.parseShort(bytes[i]);
			
			return true; 
		}
		return false;
	}
	
	public char getClas() {
		
		short firstByte = ip[0];
		if(firstByte < endAdd[0])
			return 'A';
		else if(firstByte < endAdd[1])
			return 'B';
		else if(firstByte < endAdd[2])
			return 'C';
		return '\n';
	}
	
	public short[] getDefaultSubnetMask() {
		char clas = getClas();
		
		if(clas == 'A')
			return new short[] {255,0,0,0};
		else if(clas == 'B')
			return new short[] {255,255,0,0};
		else if(clas == 'c')
			return new short[] {255,255,255,0};
		
		return null;
	}
	
	public byte getBitsRequired() {
		String bin = Integer.toBinaryString(subNetCount-1);
		return (byte) bin.length();	
	}
	
	public String getNewSubnetMask() {
		
		byte bitCount = getBitsRequired();
		
		byte temp = 0;
		char clas = getClas();
		String newSubnet = "";
		if(clas == 'A') {
			temp = 3;
			newSubnet = "11111111.";
		}
		else if (clas == 'B') {
			temp = 2;
			newSubnet = "11111111.11111111.";
		}
		else if (clas == 'C') {
			temp = 1;
			newSubnet = "11111111.11111111.11111111";
		}
	
		int j = 0;
		for(int i=0; i<(8*temp + (temp-1)); i++) {
			if(i == 8 || i==17) 
				newSubnet += ".";	
			else if(j < bitCount) {
				newSubnet += "1";
				j++;
			}
			else 
				newSubnet += "0";		
		}
		return newSubnet; 
	}
	
	public String ipToBinary(short ip[]) {
		String strIp = "";
		for(int i=0; i<4; i++) {
			strIp += ("00000000" + Integer.toBinaryString(ip[i])).substring(Integer.toBinaryString(ip[i]).length());
			if(i<3)
				strIp += ".";
		}
		return strIp;
	}
	
	public short[] ipToBytes(String ip) {
		String strBytes[] = ip.split("[.]");
		short bytes[] = new short[4];
		for(int i=0; i<4; i++) {
			bytes[i] = (short) Integer.parseInt(strBytes[i],2);
			System.out.println(bytes[i]);
		}
		return bytes;
	}
	public void printIP(short ip[]) {
		for(int i=0; i<4; i++) {
			System.out.print(ip[i]);	
			if(i<3)
				System.out.print(".");
		}
	}
	
	public static void main(String args[]) {
		
		IPAddress ipAddress = new IPAddress();
		System.out.println(ipAddress.convertToIp("54.34.43.34"));
		ipAddress.subNetCount = 4;
		System.out.println(ipAddress.getNewSubnetMask());
		System.out.println(ipAddress.ip[0]);
		
		System.out.println(ipAddress.ipToBinary(new short[] {54,34,43,34}));
		System.out.println(ipAddress.ipToBytes("54.34.43.34"));
		
		
	}
}
