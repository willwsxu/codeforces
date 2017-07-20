package education25r;


import static java.lang.System.out;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Brief Description: n tasks with difficulty A1,..., An. He can solve these problems in arbitrary order. 
 * Though he can solve problem i with difficulty Ai only if he had already solved some problem with difficulty d>=Ai
 * initially hte max difficulty he solve is k.
 * He can go to other online judges to solve some problems if he is unable to choose the problem in hist list due to difficulty constraints
 * Q: How many other problems he needs to solve so he can complete the list
*/
public class MultiJudgeSolving {
    int N, k;
    int A[];
    MultiJudgeSolving()
    {
        N = sc.nextInt(); //1 ≤ n ≤ 10^3
        k= sc.nextInt();  //1 ≤ k ≤ 10^9
        A = ria(N);
    }
    
    MultiJudgeSolving(int a[], int k)
    {
        N=a.length;
        A=a;
        this.k=k;
    }
    void solve()
    {
        Arrays.sort(A); // sort tasks from easy to hard
        int extra=0;
        int lastMax=k;
        int p=upperBound(A, -1, 2*lastMax); // find the task that is too hard A[p]>2k
        while (p<N) {
            //out.println("p="+p);
            if (p>0 && A[p-1]>k)
                lastMax = A[p-1]; // pick most difficult completed so far
            while (A[p]>2*lastMax) { // find an extra task that is twice as hard, until A[p]<=2k
                lastMax <<= 1;
                extra++;
            }
            //out.println("extra="+extra+" lastMax="+lastMax+" p="+p);
            p=upperBound(A, p-1, 2*lastMax);
        }
        out.println(extra);
    }
    
    static void test()
    {
        new MultiJudgeSolving(new int[]{2, 1, 9}, 3).solve();
        new MultiJudgeSolving(new int[]{2, 1, 24}, 3).solve();
        new MultiJudgeSolving(new int[]{2, 1, 3,3,3,3,3,24}, 3).solve();
        new MultiJudgeSolving(new int[]{10, 3, 6, 3}, 20).solve();
    }
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {        
        new MultiJudgeSolving().solve();
    }
    
    public static int[] ria(int N) { // shared code
        int L[]=new int[N];
        for (int i=0; i<N; i++)
            L[i]=sc.nextInt();
        return L;
    }   
        
    public static int upperBound(int[] a, int start, long k) { // shared code
        int n = a.length;
        if (a[n-1] <= k)
            return n;
        int l = start, r = n - 1;
        while (r - l > 1) {
            int mid = (l + r) >> 1;
            if (a[mid] > k) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }  
}
