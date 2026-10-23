import java.util.*;
public class Miller{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter n : ");
        int n = sc.nextInt();
        if (n<2){
            System.out.println("Composite");
            return;
        }
        if(n==2 || n==3){
            System.out.println("Prime");
            return;
        }
        if(n%2==0){
            System.out.println("Composite");
            return;
        }
        int m=n-1;
        int k=0;
        int b=1;
        while(m%2==0){
            m=m/2;
            k++;
        }
        int a=2;
        for ( int i=0;i<m;i++){
            b=(b*a)%n;
        }
        if (b==1 || b==n-1){
            System.out.println("Probably Prime");
            return;
        }
        for (int i=1;i<k;i++){
            b=(b*b)%n;
            if (b==1){
                System.out.println("Composite");
                return;
            }
            if(b==n-1){
                System.out.println("Probably Prime");
                return;
            }
        }
        System.out.println("Composite");
    }
}