
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.*;

public class InetDemo{
   
    public static int showMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu : ");
        System.out.println("1-> IP Address -> Domain Name ");
        System.out.println("2-> Domain Name -> IP Address : ");
        System.out.print("Select Choice : ");
        int choice = sc.nextInt();
        return choice;
    }

    public boolean isValidIP(String IP){
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        return IP.matches(PATTERN);
    }
   
    public void resolveIP(String IP){

        try{
            if(isValidIP(IP))
                System.out.println(InetAddress.getByName(IP).getHostName().toString());
            else
                System.out.println("Invalid IP Address Try Again..!");
        }catch(UnknownHostException e){
            System.out.println("Something Went Wrong..Try Later.!");
        }
       
    }
   
    public boolean isValidDomain(String domain){
       
        String regex = "^((?!-)[A-Za-z0-9-]"
                       + "{1,63}(?<!-)\\.)"
                       + "+[A-Za-z]{2,6}";
 
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(domain);
        return m.matches();
    }
   
    public void resolveDomain(String domain) {
        try{
            if(isValidDomain(domain)){
                InetAddress inetAddress = InetAddress.getByName(domain);
                System.out.println(inetAddress.getHostAddress());
            }
            else
                System.out.println("Invalid Domain..Try Again..!");
        }catch(UnknownHostException e){
                System.out.println("Something Went Wrong..Try Later.!");
        }
    }
        
    public static void main(String[] args){
        InetDemo inetDemo = new InetDemo();
        Scanner sc = new Scanner(System.in);
        int choice;

        do{ 
            choice = showMenu();
            if(choice == 1){
                System.out.print("Enter IP Address : ");
                inetDemo.resolveIP(sc.next());
            }
            
            else if(choice == 2){
                System.out.print("Enter Domain Name : ");
                inetDemo.resolveDomain(sc.next());
            }

            else
                System.out.println("Invalid Choice..!");       
                
            System.out.print("Do You Want to Continue [Yes/No] = [1/0] : ");
            if(sc.nextInt() == 0)
                break;
            System.out.println();
        }while(true);
    }
}
