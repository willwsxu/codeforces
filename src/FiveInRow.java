
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy
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
    boolean horizontal(int i, int j)
    {
        int x=1;
        int dot=0;
        if (j>6)
            return false;
        for (int k=j+1; k<10; k++) {
            if (board[i].charAt(k)=='X')
                x++;
            else if (board[i].charAt(k)=='.') {
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
        if ( j>0 && board[i].charAt(j-1)=='.')
            return true;
        if ( j<6 && board[i].charAt(j+4)=='.')
            return true;
        return false;
    }
    
    boolean vertical(int i, int j)
    {
        int x=1;
        int dot=0;
        if (i>6)
            return false;
        for (int k=i+1; k<10; k++) {
            if (board[k].charAt(j)=='X')
                x++;
            else if (board[k].charAt(j)=='.') {
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
        if ( i>0 && board[i-1].charAt(j)=='.')
            return true;
        if ( i<6 && board[i+4].charAt(j)=='.')
            return true;
        return false;
    }
    
    boolean backward(int i, int j)
    {
        int x=1;
        int dot=0;
        int ij=i>j?i-j:j-i;
        if (ij>5 || j<3 || i>6)
            return false;
        for (int k=i+1, m=j-1; k<10 && m>=0; k++, m--) {
            if (board[k].charAt(m)=='X')
                x++;
            else if (board[k].charAt(m)=='.') {
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
        if ( i>0 && j<9 && board[i-1].charAt(j+1)=='.')
            return true;
        if ( i<6 && j>0 && board[i+4].charAt(j-1)=='.')
            return true;
        return false;
    }
      
    
    boolean forward(int i, int j)
    {
        int x=1;
        int dot=0;
        int ij=i+j;
        if (ij<4 || ij>14 || j<3 || i>6)
            return false;
        for (int k=i+1, m=j-1; k<10 && m>=0; k++, m--) {
            if (board[k].charAt(m)=='X')
                x++;
            else if (board[k].charAt(m)=='.') {
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
        if ( i>0 && j<9 && board[i-1].charAt(j+1)=='.')
            return true;
        if ( i<6 && j>0 && board[i+4].charAt(j-1)=='.')
            return true;
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
        // from (0,0) to (9,9)
        for (int i=4; i<10; i++) { //r+c=i
            seq = new char[i+1];
            for (int j=0; j<=i; j++)
                seq[j]=board[i-j].charAt(j);
            if ( scanSequence(seq) )
                return true;  
            if (i==9)
                break;
            for (int j=0; j<=i; j++)
                seq[j]=board[9-j].charAt(9-i+j);    // r+c=18-i   
            if ( scanSequence(seq) )
                return true;   
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
        test2();
        //out.println(new FiveInRow().solve()?"YES":"NO");
    }
}