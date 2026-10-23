import java.util.*;
import java.io.*;
import java.net.*;
public class HillServer{
    static int[][] minor(int[][] m,int r,int c){
        int n=m.length;
        int [][] ans= new int [n-1][n-1];
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-1;j++){
                ans[i][j]=m[i<r?i:i+1][j<c?j:j+1];
            }
        }
        return ans;
    }
    static int det(int[][] m){
        if(m.length==0){
            return 1;
        }
        int d=0;
        for(int j=0;j<m.length;j++){
            d+=(j%2==0?1:-1)*m[0][j]*det(minor(m,0,j));
        }
        return d;
    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        ServerSocket ss = new ServerSocket(5000);
        Socket s= ss.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());
        String cipher=in.readUTF();
        System.out.println("Enter matrix size n : ");
        int n = sc.nextInt();
        int [][] k = new int [n][n];
        System.out.println("Enter matrix : ");
        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                k[i][j]=sc.nextInt();
            }
        }
        int det=((det(k)%26)+26)%26;
        int dinv=0;
        for(int i=1;i<26;i++)
        {
            if((det*i)%26==1) dinv=i;
        }
        if(dinv==0){
            System.out.println("Matrix not invertible");
            return;
        }

        int[][] ik=new int[n][n];
        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                int coef=((i+j)%2==0?1:-1)*det(minor(k,j,i));
                ik[i][j]=((dinv*coef)%26+26)%26;
            }
        }
        String plain="";
        for(int i=0;i<cipher.length();i+=n){
            for(int r=0;r<n;r++){
                int sum=0;
                for(int c=0;c<n;c++){
                    sum+=ik[r][c]*(cipher.charAt(i+c)-'A');
                }
                plain+=(char)(((sum%26)+26)%26+'A');
            }
        }
        System.out.println("Plain text: "+plain);


    }
}