import java.util.*;
import java.net.*;
import java.io.*;
import java.math.*;
public class RSAClient{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter m : ");
        BigInteger m = sc.nextBigInteger();
        Socket s = new Socket("localhost",5000);
        DataInputStream in = new DataInputStream(s.getInputStream());
        BigInteger e = new BigInteger(in.readUTF());
        BigInteger n = new BigInteger(in.readUTF());
        BigInteger c= m.modPow(e,n);
        System.out.println("Ciphertext : "+c);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        out.writeUTF(c.toString());
    }
}