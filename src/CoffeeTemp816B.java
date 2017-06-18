
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.lang.System.out;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// contest #419 failed on test 3, only work if k=2
public class CoffeeTemp816B {
    int t[]=new int[200000+5];
    List<Map.Entry<Integer,Integer>> r=new ArrayList<>();  //recipe
    CoffeeTemp816B()
    {
        //n, k (1 ≤ k ≤ n ≤ 200000), and q (1 ≤ q ≤ 200000)
        int n=sc.ni();
        int k=sc.ni();
        int q=sc.ni();
        for (int i=0; i<n; i++) {  //1 ≤ li ≤ ri ≤ 200000
            r.add(new SimpleEntry<>(sc.ni(), sc.ni()));
        }
        Collections.sort(r, (e1,e2)->e1.getKey()-e2.getKey());
        int start=r.get(0).getKey();
        int end=r.get(0).getValue();
        for (int i=1; i<r.size(); i++) {
            if (start>end) {
                if (r.get(i).getValue()>=start)
                    end=r.get(i).getValue();
                continue;
            }
            if (r.get(i).getKey()>end) {
                start=r.get(i).getKey();
                end=r.get(i).getValue();
                continue;
            }
            int ovs=max(start, r.get(i).getKey());
            int ove=min(end, r.get(i).getValue());
            for (int j=ovs; j<=ove; j++)
                t[j]=1;
            start=ove+1;
            end=max(end, r.get(i).getValue());
        }
        prefix = prefixSum(t);
        StringBuilder sb=new StringBuilder();
        for (int i=0; i<q; i++) {
            sb.append(query(sc.ni(), sc.ni()));
            sb.append("\n");
        }
        out.print(sb.toString());
    }
    long prefix[];
    long query(int a, int b)
    {
        return prefix[b+1]-prefix[a];
    }
    
    public static long[] prefixSum(int a[])  // set first elem to 0
    {
        long s[]=new long[a.length+1];
        s[0]=0;
        for (int i=1; i<=a.length; i++)
            s[i] = s[i-1]+a[i-1];
        return s;
    }
    static MyScanner sc = new MyScanner();
    public static void main(String[] args)
    {
        new CoffeeTemp816B();
    }
}


class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    MyScanner(String f)
    {
        try {
            br = new BufferedReader(new FileReader(new File(f)));
        } catch (IOException e)
        {
            out.println("MyScanner bad file "+f);
        }
    }
    public MyScanner() {
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

    double nextDouble() {
        return Double.parseDouble(next());
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
    public int[] ria(int N) { // read int array
        int L[]=new int[N];
        for (int i=0; i<N; i++)
            L[i]=nextInt();
        return L;
    }
    public long[] rla(int N) { // read long array
        long L[]=new long[N];
        for (int i=0; i<N; i++)
            L[i]=nextLong();
        return L;
    }
}