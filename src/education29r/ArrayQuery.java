package education29r;


import static java.lang.System.out;
import java.util.Arrays;
import java.util.Scanner;

// educational round 29, 863D
// offline reverse processing query
public class ArrayQuery {
    
    int a[];  // 1 ≤ ai ≤ 10^9, 1 ≤ n, q ≤ 2·10^5,
    int b[];  // important index of a, 1 ≤ m ≤ 100, 1 ≤ bi ≤ n
    
    static class Query {
        int type;   // 1 ≤ ti ≤ 2
        int left;   // 1 ≤ li ≤ ri ≤ n
        int right;
        Query(int t, int l, int r)
        {
            type=t; left=l; right=r;
        }
    }
    Query q[];
    
    ArrayQuery()
    {
        int n=sc.nextInt();
        int Q=sc.nextInt();
        int m=sc.nextInt();
        a= new int[n];
        for (int i=0; i<n; i++)
            a[i]=sc.nextInt();
        q=new Query[Q];
        for (int i=0; i<Q; i++) {
            int t=sc.nextInt();
            int l=sc.nextInt();
            int r=sc.nextInt();            
            q[i]=new Query(t,l,r);
        }
        b=new int[m];
        for (int i=0; i<m; i++)
            b[i]=sc.nextInt();
        reverseQuery();
    }
    ArrayQuery(int a[], Query q[], int b[] )
    {
        this.a=a;
        this.q=q;
        this.b=b;
        reverseQuery();
    }
    
    // trace pos after reverse
    int reverseA(int pos, int left, int right)
    {
        if (pos<=right && pos>=left) {
            return right+left-pos;  // pos-left=right-rev
        }
        return pos;
    }
    int cyclicShiftRightA(int pos, int left, int right)
    {
        if (pos==right)
            return left;
        else if (pos<right && pos>=left) {
            return pos+1;
        }
        return pos;
    }
    int cyclicShiftLeftA(int pos, int left, int right)
    {
        if (pos==left)
            return right;
        else if (pos<=right && pos>left) {
            return pos-1;
        }
        return pos;
    }
    void reverseQuery() {
        for (int i=q.length-1; i>=0; i--){
            for (int j=0; j<b.length; j++) { // trace back pos in b
                if (q[i].type==1) {
                    b[j] = cyclicShiftLeftA(b[j], q[i].left, q[i].right); //shift reverse
                } else {
                    b[j] = reverseA(b[j], q[i].left, q[i].right);                    
                }
            }
            //out.println(Arrays.toString(b));
        }
        StringBuilder sb=new StringBuilder();
        for (int j=0; j<b.length; j++) {
            sb.append(a[b[j]-1]);
            sb.append(' ');
        }
        out.println(sb.toString());
    }
    
    public static void test()
    {
        Query[]q=new Query[3];
        q[0]=new Query(2,1,3);
        q[1]=new Query(2,3,6);
        q[2]=new Query(1,1,6);
        new ArrayQuery(new int[]{1,2,3,4,5,6}, q, new int[]{2,2,1,5,3});
    }
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        new ArrayQuery();
    }
}
