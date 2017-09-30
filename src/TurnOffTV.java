
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

// educational round 29, 863E
// key ideas:
// segment cover range 10^9, too large, compress because of integer moments
//   compress interval [l,r] into 3 integers l-1, l, r
// sort moments to speed up look up, TreeSet, List binarySearch
// count inverval coverage using prefixsum, CNTl += 1, CNTr+1 -= 1
// prefix sum of moments of coverage 1, prefr  == prefl - 1 ok to remove [l,r]
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
        if (N==1) {
            
        }
        TreeSet<Integer> moments=new TreeSet<>(); 
        for (int i=0; i<N; i++)
        {
            moments.add(sg[i].left);
            if ( sg[i].left>0)
                moments.add(sg[i].left-1);
            moments.add(sg[i].right);
        }
        diffCount = new int[moments.size()+1];
        List<Integer> mm= new ArrayList(moments);
        //out.println(mm);
        for (int i=0; i<N; i++)
        {
            int l=Collections.binarySearch(mm, sg[i].left);
            int r=Collections.binarySearch(mm, sg[i].right);
            //out.println("l="+l+" r="+r);
            diffCount[l] += 1;
            diffCount[r+1] -= 1;
        }
        //out.println(Arrays.toString(diffCount));
        prefixSum = new int[diffCount.length];
        prefixSum[0]=diffCount[0];
        int cover1[] = new int[diffCount.length];
        for (int i=1; i<diffCount.length; i++) {
            prefixSum[i] = prefixSum[i-1]+diffCount[i];
            if (prefixSum[i]==1)
                cover1[i]=1;
            else
                cover1[i]=0;
        }
        cover1[0]=0;
        for (int i=1; i<cover1.length; i++) {
            cover1[i] += cover1[i-1];
        }
        //out.println(Arrays.toString(prefixSum));
        //out.println(Arrays.toString(cover1));
        for (int i=0; i<N; i++)
        {
            int l=Collections.binarySearch(mm, sg[i].left);
            int r=Collections.binarySearch(mm, sg[i].right);
            if (prefixSum[l]<2 || prefixSum[r]<2)
                continue;
            if (l==0 && cover1[r]==0 || l>0 && cover1[l-1]==cover1[r]) {
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
        new TurnOffTV(2, sg);   // 1
        sg[0]=new Segment(1,3);
        sg[1]=new Segment(4,6);
        sg[2]=new Segment(1,7);                
        new TurnOffTV(3, sg);  // 1
        sg[0]=new Segment(1,2);
        sg[1]=new Segment(3,4);   
        sg[2]=new Segment(7,8);            
        new TurnOffTV(3, sg);     // -1
        sg[0]=new Segment(1,2);
        sg[1]=new Segment(2,3);
        sg[2]=new Segment(3,4);               
        new TurnOffTV(3, sg);     // 2  
        sg[0]=new Segment(3687,7959);
        sg[1]=new Segment(4918,9822);
        sg[2]=new Segment(8048,11728);               
        new TurnOffTV(3, sg);            //-1   
        sg[0]=new Segment(0,0);         
        new TurnOffTV(1, sg);            //-1, special case  
        sg[0]=new Segment(0,0);             
        sg[1]=new Segment(0,1);         
        new TurnOffTV(2, sg);            //1  
    }
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        new TurnOffTV();
        //test();
    }
}
