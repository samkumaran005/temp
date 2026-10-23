import java.io.*;
import java.net.*;
import java.util.*;
public class AffineClient{
    public static void main(String [] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a and b : ");
        int a= sc.nextInt();
        int b= sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Plain text: ");
        String text = sc.nextLine().toUpperCase().replaceAll("[^A-Z]","");
        String cipher="";
        for(char ch : text.toCharArray()){
            cipher+=(char)((((a*(ch-'A')+b))%26)+'A'); // imp line
        }
        System.out.println(cipher);
        Socket s = new Socket("localhost",5000);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        out.writeInt(a);
        out.writeInt(b);
        out.writeUTF(cipher);
        s.close();
    }
}