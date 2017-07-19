
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
    int solve()
    {
        Arrays.sort(A);
        int extra=0;
        int p=lowerBound(A, 2*k);
        while (p<N) {
            
        }
        return extra;
    }
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {        
        
    }
    
    public static int[] ria(int N) { // shared code
        int L[]=new int[N];
        for (int i=0; i<N; i++)
            L[i]=sc.nextInt();
        return L;
    }   
    
    public static int lowerBound(int[] a, long k) { // shared code
        int n = a.length;
        if (a[n-1] < k)
            return n;
        int l = -1, r = n - 1;
        while (r - l > 1) {
            int mid = (l + r) >> 1;
            if (a[mid] >= k) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }  
}
