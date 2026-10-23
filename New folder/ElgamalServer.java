import java.util.*;
import java.io.*;
import java.net.*;
import java.math.*;

public class ElgamalServer{
    public static void main(String [] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        ServerSocket ss = new ServerSocket(5000);
        Socket s = ss.accept();
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());
        BigInteger q = new BigInteger(in.readUTF());
        BigInteger a = new BigInteger(in.readUTF());
        System.out.println("Enter xa : ");
        BigInteger xa = sc.nextBigInteger();
        BigInteger ya = a.modPow(xa,q);
        out.writeUTF(ya.toString());
        BigInteger c1= new BigInteger(in.readUTF());
        BigInteger c2= new BigInteger(in.readUTF());
        System.out.println("Encrypted Message:"+c1+" "+c2);
        BigInteger K= c1.modPow(xa,q);
        BigInteger m = c2.multiply(K.modInverse(q)).mod(q);
        System.out.println("Decrypted Message : "+m);
    }
}