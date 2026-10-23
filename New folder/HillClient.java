import java.util.*;
import java.io.*;
import java.net.*;
public class HillClient{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        Socket s= new Socket("localhost",5000);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        System.out.println("Enter plain text : ");
        String plain=sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");;
        System.out.println("Enter matrix size n : ");
        int n = sc.nextInt();
        int [][] k = new int [n][n];
        System.out.println("Enter matrix : ");
        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                k[i][j]=sc.nextInt();
            }
        }
        String cipher="";
        while(plain.length()%n!=0) plain+='X';
        for(int i=0;i<plain.length();i+=n){
            for(int r=0;r<n;r++){
                int sum=0;
                for(int c=0;c<n;c++){
                    sum+=k[r][c]*(plain.charAt(i+c)-'A');
                }
                cipher+=(char)(sum%26+'A');
            }
        }
        System.out.println("Cipher text: "+cipher);
        out.writeUTF(cipher);
    }
}