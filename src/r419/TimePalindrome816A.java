package r419;


import static java.lang.System.out;
import java.util.Scanner;

//#419, div2, fail on test43
public class TimePalindrome816A {
    
    static int palindrome(String hm)
    {
        String s[]=hm.split(":");
        String reverse=new StringBuilder(s[0]).reverse().toString();
        int min1=Integer.parseInt(s[1]);
        int min2=Integer.parseInt(reverse);
        if (min1==min2)
            return 0;
        if (min2>min1 && min2<60)
            return min2-min1;
        int hour=Integer.parseInt(s[0]);
        hour++;
        int ans=60-min1;
        if (hour==24)
            return ans;
        if (hour>5 && hour<10) {
            ans += 60*(10-hour);
            ans++; //10:01
            return ans;
        }
        if (hour>15 && hour<20) {
            ans += 60*(20-hour);
            ans +=02; // 20:02
            return ans;
        }
        int min3=(hour%10)*10+hour/10;
        return ans+min3;
    }
    static void test()
    {
        out.println(palindrome("05:39"));
        out.println(palindrome("13:31"));
        out.println(palindrome("23:59"));
        out.println(palindrome("05:51"));
        out.println(palindrome("16:06")); 
        out.println(palindrome("06:59"));   // edge case, test 43      
    }
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        //test();
        out.println(palindrome(sc.next()));
    }
}
