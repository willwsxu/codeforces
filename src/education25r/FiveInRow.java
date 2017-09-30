package education25r;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Arrays;
import java.util.Scanner;

// educational round 25, 825A
/*
 * place X on 10x10 grid, check if going to win in next move
 * no one won on current config
 */

public class FiveInRow {
    String []board=new String[10];
    
    static boolean checkOne(char[] seq, int s)
    {
        int x=1;
        int dot=0;
        int end=s+1;
        //out.println(Arrays.toString(seq)+" "+s);
        for (; end<seq.length; end++) {
            if (seq[end]=='X') {
                x++;
            } else if (seq[end]=='.') {
                if (dot>0)
                    return false;
                dot++;            
            }
            else
                break;
            if (dot+x==5)
                break;            
        }
        if (x<4)
            return false;
        if (dot==1)
            return true;
        if ( s>0 && seq[s-1]=='.')
            return true;
        if (end==seq.length)
            return false;
        return seq[end]=='.';
    }
    // find any 5 in a row in a sequence
    static boolean scanSequence(char[] seq)
    {
        //out.println(Arrays.toString(seq)+" scan");
        if (seq.length<5)
            return false;
        boolean skip=false;
        for (int i=0; i<seq.length; i++) {
            if (seq.length-i<4)
                return false;
            if (seq[i]=='X') {
                if (skip)
                    continue;
                if (checkOne(seq, i))
                    return true;
                skip=true;  // skip next X
            } else
                skip=false;
        }
        return false;
    }
          
    FiveInRow()
    {
        for (int i=0; i<board.length; i++)
            board[i]=sc.next();
    }
    
    boolean solve()
    {
        char seq[]=new char[board.length];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length(); j++) //horizontal
                seq[j]=board[i].charAt(j);
            if ( scanSequence(seq) )
                return true;
            
            for (int j=0; j<board[i].length(); j++) //vertical
                seq[j]=board[j].charAt(i);
            if ( scanSequence(seq) )
                return true;            
        }
        for (int i=4; i<10; i++) { 
            // from (0,0) to (9,9), forward diagonal
            //r+c=i
            seq = new char[i+1];
            for (int j=0; j<=i; j++)
                seq[j]=board[i-j].charAt(j);
            if ( scanSequence(seq) )
                return true;  
            if (i<9) {
                for (int j=0; j<=i; j++)
                    seq[j]=board[9-j].charAt(9-i+j);    // r+c=18-i   
                if ( scanSequence(seq) )
                    return true;  
            }
            // from (9, 0) to (0,9), backward diagonal 
            for (int j=i; j>=0; j--)
                seq[j]=board[j+9-i].charAt(j); // r-c=9-i
            if ( scanSequence(seq) )
                return true;  
            if (i<9) {
                for (int j=9-i; j<10; j++)
                    seq[j+i-9]=board[j+i-9].charAt(j); //c-r=9-i
                if ( scanSequence(seq) )
                    return true;  
            }
        }
        return false;
    }
    
    static void test()
    {
        out.println(scanSequence(new char []{'X', 'X', 'X', 'X', '.'}));
        out.println(scanSequence(new char []{'.', 'X', 'X', 'X', 'X'}));
        out.println(scanSequence(new char []{'X', 'X', '.', 'X', 'X'}));
        out.println(scanSequence(new char []{'X', 'X', 'X', 'X', 'O'}));
        out.println(scanSequence(new char []{'X', 'X', '.', 'X', '.'}));
        out.println(scanSequence(new char []{'X', 'X', '.', 'X', '.','X','X','X'}));
    }
   
    public static Scanner getFileScanner(String file)
    {
        try {
            return new Scanner( new FileReader( new File(file)));
        }
        catch (IOException e)
        {
        }
        return new Scanner(System.in);
    }
    static void test2()
    {
        sc=getFileScanner(".\\test\\FiveInARow-t.txt");
        int T=sc.nextInt();
        while (T-->0) {
            String result=new FiveInRow().solve()?"YES":"NO";
            String expect = sc.next();
            out.println(result.equals(expect)?"Pass":"Fail");
        }
    }

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {     
        out.println(new FiveInRow().solve()?"YES":"NO");
    }
}
