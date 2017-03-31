
package codeforces;

// complete binary tree from 1 to N

import static java.lang.System.out;
import java.util.Arrays;
import java.util.Scanner;

// N+1 = 2^k, root node is (N+1)/2
// left side is smaller than parent, right side is bigger
class ICBT
{
    int bt[];
    public ICBT(int N)
    {
        bt = new int[N+1];
        bt[0] = N;
        fill(1, (N+1)/2, 0);
    }
    void fill(int r, int val, int rigthExtra)
    {
        if ( r>bt[0])
            return;
        bt[r]=val+rigthExtra;
        fill(2*r, val/2, rigthExtra);
        fill(2*r+1, val/2, rigthExtra+val);
    }
    int parent(int m) {
        if ( m==1)
            return m;
        return m/2;
    }
    int left(int m)
    {
        int l = (m<<1);
        return l<=bt[0]?l:m;
    }
    int right(int m)
    {
        int r = (m<<1)+1;
        return r<=bt[0]?r:m;
    }
    void print()
    {
        out.println(Arrays.toString(bt));
    }
    int find(int ind, int v) {
        if (bt[ind]==v)
            return ind;
        else if (v<bt[ind])
            return find(left(ind), v);
        else
            return find(right(ind), v);
    }
    void solve(String q, int v)
    {
        int ind=find(1, v); // find the vertex with value v
        //out.println(ind);
        for (char m: q.toCharArray()) {
            switch(m) {
                case 'U':
                    ind = parent(ind);
                    break;
                case 'R':
                    ind = right(ind);
                    break;
                case 'L':
                    ind = left(ind);
                    break;
            }
            //out.println(ind);
        }
        out.println(bt[ind]);
    }
}
public class PathBT {
    
    static Scanner scan = new Scanner(System.in);
    public static void autoTest()
    {
        int N = scan.nextInt();   // 1 and 10^18
        int TC = scan.nextInt();  // between 1 and 6
        for (int i=0; i<TC; i++) {
            int v = scan.nextInt(); // 
            String q = scan.nextLine();
            if (q.isEmpty())
                q = scan.nextLine();
            new ICBT(N).solve(q, v);
        }
    }
    public static void main(String[] args) {
        autoTest();
    }
}
