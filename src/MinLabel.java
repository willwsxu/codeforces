
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
        PriorityQueue<IntPair> sorted = new PriorityQueue<>((e1,e2)->e1.int2()-e2.int2());
        for (int i=0; i<dag.V(); i++) {
            List<Integer> a = dag.adj(i);
            IntPair e=new IntPair(i+1, a.size());
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
            for (int j=0; j<r.size(); j++) {
                List<Integer> uL=dag.adj(r.get(j)); // find u conneced to v
                IntPair out = new IntPair(r.get(j)+1, uL.size()-1);
                sorted.add(out);  // let old items become obsolete, don't call remove
                uL.remove(new Integer(e.int1()-1));
            }
        }
        for (int b: ans) {
            out.print(b);
            out.print(" ");
        }
        out.println();
    }
        
    static Scanner sc = new Scanner(System.in);
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
