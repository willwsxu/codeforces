package education25r;


import static java.lang.System.out;
import java.util.Scanner;

/*
 * educational round 25, 825A
 * convert decimal number string to binary, each digit is encoded as # of 1's, digit are separated by a 0
 */
public class BinaryProtocol {

    static long decode(String s) {
        int count=0;
        long ans=0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i)=='1')
                count++;
            else {
                ans = ans*10 +count;
                count=0;
                if (i==s.length()-1) // special case that 0 is not separator if it is at end
                    ans *= 10;
            }
        }
        if (count>0)
            ans = ans*10 +count;
        return ans;
    }
    
    static String decode2(String s) {
        int count=0;
        StringBuilder ans=new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i)=='1')
                count++;
            else {
                ans.append(count);
                count=0;
            }
        }
        if (count>0)
            ans.append(count);
        return ans.toString();
    }
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {        
        int N = sc.nextInt();  // 1≤ n ≤ 89
        String s=sc.next();
        out.println(decode(s));
        out.flush();
    }
}
