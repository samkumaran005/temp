import java.io.*;
import java.net.*;
import java.util.*;
public class AffineServer{
    public static void main(String [] args) throws Exception{
        ServerSocket ss= new ServerSocket(5000);
        Socket s= ss.accept();
        System.out.println("Connected to client");
        DataInputStream in = new DataInputStream(s.getInputStream());
        int a= in.readInt();
        int b= in.readInt();
        String cipher = in.readUTF();
        String plain="";
        int inv=0;
        for (int i =1;i<26;i++){
            if((a*i)%26==1){
                inv =i;
            }
        }
        for(char ch : cipher.toCharArray()){
            plain+=(char)((((inv*((ch-'A')-b))%26+26)%26)+'A'); // imp line
        }
        System.out.println("Plain text: "+ plain);
        s.close();
        ss.close();
    }
}