
import static java.lang.System.out;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

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
    }
    
    void solve()
    {
        // sort vertex by outgoing edges
        TreeSet<Map.Entry<Integer,Integer>> sorted = new TreeSet<>((e1,e2)->e1.getValue()-e2.getValue());
        for (int i=0; i<dag.V(); i++) {
            List<Integer> a = dag.adj(i);
            sorted.add(new AbstractMap.SimpleEntry(i+1, a.size()));
        }
        int ans[]=new int[dag.V()];
        int label=dag.V();
        while (!sorted.isEmpty()) {
            Map.Entry<Integer,Integer> e = sorted.pollFirst();
            ans[e.getKey()-1]=label--;
            
            // remove any edge connected to this vertex
            List<Integer> r = dagr.adj(e.getKey()-1);
            for (int j=0; j<r.size(); j++) {
                List<Integer> uL=dag.adj(r.get(j)); // find u conneced to v
                Map.Entry<Integer,Integer> out = new AbstractMap.SimpleEntry(r.get(j)+1, uL.size());
                sorted.remove(out);
                out.setValue(out.getValue()-1);
                sorted.add(out);
                uL.remove(new Integer(e.getKey()-1));
            }
        }
        out.println(Arrays.toString(ans));
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
}
