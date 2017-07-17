
import static java.lang.System.out;
import java.util.Scanner;

/*
 * educational round 25, A
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
