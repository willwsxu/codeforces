
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

// educational round 25, 825E
// TreeSet can not store item when IntPair.second is not unique, even overrides
// HashSet can store IntPair even if second is duplicate
// For this reason, PriorityQUeue is chosen to sort outgoing edges
public class MinLabel {
    DAG dag, dagr;
    MinLabel()
    {
        int n=sc.nextInt(); //2 ≤ n ≤ 10^5, 1 ≤ m ≤ 10^5
        int m=sc.nextInt();
        dag = new DAG(n);
        dagr = new DAG(n);
        for (int i=0; i<m; i++) {
            int u=sc.nextInt();
            int v=sc.nextInt();
            dag.addEdge(u-1, v-1);
            dagr.addEdge(v-1, u-1);
        }
        //dag.print();
        //dagr.print();
    }
    
    void solve()
    {
        // sort vertex by outgoing edges
        Comparator<IntPair> cmp1 = (e1,e2)->e1.int2()-e2.int2();
        Comparator<IntPair> cmp2 = (e1,e2)->e2.int1()-e1.int1();
        PriorityQueue<IntPair> sorted = new PriorityQueue<>(cmp1.thenComparing(cmp2));
        int outgoing[]=new int[dag.V()];
        for (int i=0; i<dag.V(); i++) {
            List<Integer> a = dag.adj(i);
            outgoing[i]=a.size();
            IntPair e=new IntPair(i+1, outgoing[i]);
            if ( outgoing[i]<=1 )
                sorted.add(e);
        }
        //out.println(sorted);
        int ans[]=new int[dag.V()];
        Arrays.fill(ans, -1);
        int label=dag.V();
        while (!sorted.isEmpty()) {
            IntPair e = sorted.poll();
            if ( ans[e.int1()-1]>=0)  // obsolete items
                continue;
            ans[e.int1()-1]=label--;
            
            // remove any edge connected to this vertex
            List<Integer> r = dagr.adj(e.int1()-1);
            for (int j=0; j<r.size(); j++) {  // going through each vertex connected to e
                outgoing[r.get(j)]--;
                if ( outgoing[r.get(j)] ==0 ) {
                    IntPair out = new IntPair(r.get(j)+1, outgoing[r.get(j)]);
                    sorted.add(out);  // let old items become obsolete, don't call remove
                }
            }
        }
        fastPrint(ans);
    }
        
    static void fastPrint(int ans[])
    {
        StringBuilder sb = new StringBuilder();
        for (int b: ans) {
            sb.append(b);
            sb.append(" ");
        }
        out.println(sb.toString());        
    }
    static MyScannerX sc = new MyScannerX();
    public static void main(String[] args)
    {        
        new MinLabel().solve();
    }
}

class DAG { // unweighted, DAG
    protected int   V; // number of vertices
    private   int   E; // number of edges

    private List<List<Integer>> adj;
    public DAG(int V)
    {
        adj=new ArrayList<>(V);
        this.V = V;
        E=0;
        for (int v = 0; v < V; v++) // Initialize all lists
            adj.add( new ArrayList<>(10));
    }
    public int V() { return V; }
    public int E() { return E; }
    
    public void addEdge(int u, int v)
    {
        //out.println("add edge "+u+","+v);
        adj.get(u).add(v);
        E++;
    }
    public List<Integer> adj(int u)
    {
        return adj.get(u);
    }
    void print()
    {
        out.println(adj);
    }
}


class IntPair  // pair of int
{
    int first;
    int second;
    public IntPair(int f, int s)
    {
        first=f;
        second=s;
    }
    public int int1()
    {
        return first;
    }
    public int int2()
    {
        return second;
    }
    @Override
    public boolean equals(Object s)
    {
        out.println(this.toString()+" equal "+s.toString());
        if (s instanceof IntPair) {
            IntPair other =(IntPair)s;
            return first==other.first && second==other.second;
        }
        return false;
    }
    @Override
    public int hashCode()
    {
        out.println("hashCode");
        return (int)(first*second);
    }
    @Override
    public String toString()
    {
        return first+":"+second;
    }
}

class MyScannerX {
    BufferedReader br;
    StringTokenizer st;
    public MyScannerX() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    String nextLine(){
        String str = "";
        try {
           str = br.readLine();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return str;
    }
    
    public int ni()
    {
        return nextInt();
    }     
    public long nl()
    {
        return nextLong();
    }   
}