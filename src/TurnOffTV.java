
import static java.lang.System.out;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;


public class TurnOffTV {
    
    static class Segment
    {
        int left;
        int right;
        Segment(int l, int r)
        {
            left=l;
            right=r;
        }
    }
    int diffCount[];
    int prefixSum[];
    Segment sg[];  //0 ≤ li ≤ ri ≤ 10^9, need to compress time interval
    // compress segment [l,r] into 3 moments, l-1, l, and r, why this work?
    TreeSet<Integer> moments=new TreeSet<>(); 
    
    TurnOffTV()
    {
        int n=sc.nextInt();
        sg=new Segment[n];
        for (int i=0; i<n; i++)
        {
            int l=sc.nextInt();
            int r=sc.nextInt();       
            sg[i]=new Segment(l,r);
        }
        solve(n);
    }
    
    TurnOffTV(int N, Segment sg[])
    {
        this.sg=sg;
        solve(N);
    }
    void solve(int N)
    {
        for (int i=0; i<N; i++)
        {
            moments.add(sg[i].left);
            if ( sg[i].left>0)
                moments.add(sg[i].left-1);
            moments.add(sg[i].right);
        }
        diffCount = new int[moments.size()+1];
        for (int i=0; i<N; i++)
        {
            int l=moments.headSet(sg[i].left).size();
            int r=moments.headSet(sg[i].right).size();
            //out.println("l="+l+" r="+r);
            diffCount[l] += 1;
            diffCount[r+1] -= 1;
        }
        //out.println(Arrays.toString(diffCount));
        prefixSum = new int[diffCount.length];
        prefixSum[0]=diffCount[0];
        for (int i=1; i<diffCount.length; i++) {
            prefixSum[i] = prefixSum[i-1]+diffCount[i];
        }
        //out.println(Arrays.toString(prefixSum));
        for (int i=0; i<sg.length; i++)
        {
            int l=moments.headSet(sg[i].left).size();
            int r=moments.headSet(sg[i].right).size();
            if (prefixSum[l]>1 && prefixSum[r]>1) {
                out.println(i+1);
                return;
            }
        }
        out.println(-1);
    }
    public static void test()
    {
        Segment sg[]=new Segment[3];
        sg[0]=new Segment(0,10);
        sg[1]=new Segment(0,10); 
        new TurnOffTV(2, sg);   
        sg[0]=new Segment(1,3);
        sg[1]=new Segment(4,6);
        sg[2]=new Segment(1,7);                
        new TurnOffTV(3, sg);  
        sg[0]=new Segment(1,2);
        sg[1]=new Segment(3,4);   
        sg[2]=new Segment(7,8);            
        new TurnOffTV(3, sg);     
        sg[0]=new Segment(1,2);
        sg[1]=new Segment(2,3);
        sg[2]=new Segment(3,4);               
        new TurnOffTV(3, sg);                       
    }
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        new TurnOffTV();
    }
}
