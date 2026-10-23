import java.util.*;
import java.net.*;
import java.io.*;
import java.math.*;
public class RSAServer{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter p,q,e : ");
        BigInteger p = sc.nextBigInteger();
        BigInteger q = sc.nextBigInteger();
        BigInteger e = sc.nextBigInteger();
        BigInteger n= p.multiply(q);
        BigInteger phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger d= e.modInverse(phi);
        ServerSocket ss= new ServerSocket(5000);
        Socket s= ss.accept();
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());
        out.writeUTF(e.toString());
        out.writeUTF(n.toString());
        BigInteger c= new BigInteger(in.readUTF());
        BigInteger m= c.modPow(d,n);
        System.out.println("Decrypted Message : "+m);
        s.close();
        ss.close();
    }
}