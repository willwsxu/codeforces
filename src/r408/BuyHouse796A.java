package r408;


import static java.lang.System.out;
import java.util.Scanner;


public class BuyHouse796A {
    
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {        
        int N = scan.nextInt();  // 2 ≤ n ≤ 100, 1 ≤ m ≤ n, 1 ≤ k ≤ 100
        int m = scan.nextInt();  // girl's hourse
        int k = scan.nextInt();  // money
        int A[]=new int[N]; // house price, 0 ≤ ai ≤ 100
        int d=999999; //
        m--;  // index from 0
        for (int i=0; i<N; i++) {
            A[i]=scan.nextInt();
            if (A[i]>0 && A[i]<=k) {
                int d1 = m>i?(m-i):(i-m);
                if (d1<d)
                    d=d1;
            }
        }
        out.println(d*10);
    }
}
