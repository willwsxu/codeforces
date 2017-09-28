
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
    int segments[];  //0 ≤ li ≤ ri ≤ 10^9, need to compress time interval
    // compress segment [l,r] into 3 moments, l-1, l, and r, why this work?
    TreeSet<Integer> moments=new TreeSet<>(); 
    
    TurnOffTV(int N, int sg[])
    {
        
    }
}
