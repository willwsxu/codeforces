package education25r;


import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * 825D
 * Any two letters can be swapped positions, these operations can be performed 
 * arbitrary number of times over any pair of positions. Among all resulting strings s, 
 * you choose the one with the largest number of non-intersecting occurrences of string t. 
 * Suitability is this number of occurrences.

 * You should replace all '?' characters with small Latin letters in such a way 
 * that the suitability of string s is maximal.
 */

public class SuitableReplacement {
    SuitableReplacement()
    {
        String s=sc.next();  // 1 ≤ |s| ≤ 10^6
        String t=sc.next();  // 1 ≤ |s| ≤ 10^6
        solve(s,t);
    }
    static final int ALPHA=26;
    int []letters1=new int[ALPHA];        
    int []letters2=new int[ALPHA];
    int q=0;
    SuitableReplacement(String s, String t)
    {
        solve(s,t);
    }
    void solve(String s, String t)
    {
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i)=='?')
                q++;
            else {
                letters1[s.charAt(i)-'a']++;
            }
        }
        if (q==0) {
            out.println(s);
            return;
        }
        for (int i=0; i<t.length(); i++)  {
            letters2[t.charAt(i)-'a']++;
        }
        //out.println(Arrays.toString(letters1));
        //out.println(Arrays.toString(letters2));
        List<Character> replace=new ArrayList<>();
        while (q>0) {
            //out.println("q="+q);
            for (int i =0; i<ALPHA; i++) {
                if (letters2[i]==0)
                    continue;
                if (letters1[i]>=letters2[i])
                    letters1[i] -= letters2[i];
                else if (q>0) // need to replace ?
                {
                    int c=letters2[i]-letters1[i];
                    q -= c;
                    while (c-->0)
                        replace.add((char)('a'+i));
                    letters1[i]=0; //bug fix, reset to 0
                }
                else
                    break;
            }
        }
        StringBuilder sb=new StringBuilder();
        int r=0; // replacement index
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i)=='?')
                sb.append(replace.get(r++));
            else
                sb.append(s.charAt(i));
        }
        out.println(sb.toString());
    }
    static void test()
    {
        new SuitableReplacement("?aa?", "ab");
        new SuitableReplacement("??b?", "za");
        new SuitableReplacement("abcd", "abacaba");
    }
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {     
        new SuitableReplacement();
    }
}
