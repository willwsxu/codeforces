
// complete binary tree from 1 to N

import static java.lang.System.out;
import java.util.Arrays;
import java.util.Scanner;
// 792D
public class PathBT {
    
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        scan = codeforces.CodeChef.getFileScanner("binaryTree792d-t17.txt");
        long big = scan.nextLong();
        if (big>Long.MAX_VALUE)
            return;
        int N = (int)big;//scan.nextInt();   // 1 and 10^18
        int TC = scan.nextInt();  // between 1 and 6
        for (int i=0; i<TC; i++) {
            long v = scan.nextLong(); // v<=n
            String q = scan.nextLine();
            if (q.isEmpty())
                q = scan.nextLine();
            //new ICBT(N).solve(q, v);
            new ICBT(big).solve(q, v);
        }
    }
}

// a virtual or imaginary complete binary search tree without real storage  (in contrast to CBT class)
// store values 1 to N in a specific way: any value on the left if smaller than tree on the right
class ICBT
{
    long N;
    long root;
    ICBT(long N)
    {
        this.N = N;
        root = (N+1)/2;
        //out.println(N);
        //out.println(root);
    }
    long parent(long m) {
        if ( m==1)
            return m;
        return m/2;
    }
    long left(long m)
    {
        long l = (m<<1);
        return l<=N?l:m;
    }
    long right(long m)
    {
        long r = (m<<1)+1;
        return r<=N?r:m;
    }
    long find(long r, long val, long rigthExtra, long target)
    {
        if ( r>N) {
            out.println("r="+r+" N="+N+" val="+val+" rigthExtra="+rigthExtra+" target="+target);
            return 0;
        }
        if (target ==val+rigthExtra)
            return r;
        else if (target < val+rigthExtra)
            return find(2*r, val/2, rigthExtra, target);
        else
            return find(2*r+1, val/2, rigthExtra+val, target);
    }
    
    long findValue(long index)
    {
        // first find which tree level by multiplying 2
        if ( index>N)
            return 0;
        long leadValue=root;
        long leadIndex=1;
        while ( 2*leadIndex <= index) {
            leadIndex *= 2;
            leadValue /= 2;
        }
        return leadValue+leadValue*2*(index-leadIndex);
    }
    void solve(String q, long v)
    {
        long ind=find(1, root, 0, v); // find the vertex with value v
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
        //out.println(ind);
        out.println(findValue(ind));
    }
}
// N+1 = 2^k, root node is (N+1)/2
// left side is smaller than parent, right side is bigger
// only works if N is int. array max size is max int. too much memory for long
class CBT
{
    int bt[];
    public CBT(int N)
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
        //out.println(ind);
        out.println(bt[ind]);
    }
}