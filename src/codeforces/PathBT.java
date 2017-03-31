
package codeforces;

// complete binary tree from 1 to N

import static java.lang.System.out;
import java.util.Arrays;

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
            return bt[m];
        return bt[m/2];
    }
    void print()
    {
        out.println(Arrays.toString(bt));
    }
}
public class PathBT {
    
    public static void main(String[] args) {
        new ICBT(15).print();
    }
}
