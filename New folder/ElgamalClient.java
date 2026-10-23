import java.util.*;
import java.io.*;
import java.net.*;
import java.math.*;

public class ElgamalClient{
    public static void main(String [] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        Socket s = new Socket("localhost",5000);
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());
        System.out.println("Enter k,q,a,m");
        BigInteger k = sc.nextBigInteger();
        BigInteger q = sc.nextBigInteger();
        BigInteger a = sc.nextBigInteger();
        BigInteger m = sc.nextBigInteger();
        System.out.println("Message:"+m);
        out.writeUTF(q.toString());
        out.writeUTF(a.toString());
        BigInteger ya=new BigInteger(in.readUTF());
        BigInteger K= ya.modPow(k,q);
        BigInteger c1= a.modPow(k,q);
        BigInteger c2= K.multiply(m).mod(q);
        System.out.println("Encrypted Message:"+c1+" "+c2);
        out.writeUTF(c1.toString());
        out.writeUTF(c2.toString());
    }
}