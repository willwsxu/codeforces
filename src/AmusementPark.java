
import static java.lang.System.out;
import java.util.Scanner;

// 795A
public class AmusementPark {
    
    int adult;
    int pupil;
    int n;
    int c1;
    int c2;
    long minCost=0;
    
    long formula(int x)
    {
        return c1+c2*(x-1)*(x-1);
    }
    long calc(int group)
    {
        if (group==1)
        {
            return formula(n);
        }
        int avg = n/group;
        int rem = n%group;
        long cost = formula(avg);
        if ( rem>0 ) {
            cost *= (group-rem);
            return cost + rem*formula(avg+1);
        }
        else 
            return cost *group;
    }
    long cost(int lo, int hi, long lowCost, long hiCost)
    {
        if (lo-hi<=1 && lo-hi>=-1) {
            return lowCost;
        }
        int mid = (lo+hi)/2;
        long midCost = calc(mid);
        //out.println("first "+lo+" second "+hi+ " mid cost "+midCost+" low clost "+lowCost+" hi cost "+hiCost);
        if (midCost<lowCost)
            return cost(mid, lo, midCost, lowCost);
        else if ( midCost < hiCost )
            return cost(lo, mid, lowCost, midCost);
        else {
            out.println("bad mid cost "+midCost+" low clost "+lowCost+" hi cost "+hiCost);
            return 0;
        }
    }
    AmusementPark(int a, int p, int c1, int c2)
    {
        adult = a;
        pupil = p;
        n = a+p;
        this.c1 = c1;
        this.c2 = c2;
        long cost1 = calc(1);
        long cost2 = calc(a);
        long cost=0;
        if (cost1>cost2)
            out.println(cost(a, 1, cost2, cost1));
        else if (cost1<cost2)
            out.println(cost(1, a, cost1, cost2));
        else
            out.println(cost1);
    }
    
    static Scanner scan = new Scanner(System.in);
    static void autoTest()
    {        
        int n = scan.nextInt();  // between 1 and 20000
        int c1 = scan.nextInt();  // between 1 and 10^7
        int c2 = scan.nextInt();  // between 1 and 10^7
        String people = scan.nextLine();
        if (people.isEmpty())
            people = scan.nextLine();
        int pupil=0;
        int adult=0;
        for(int i=0; i<people.length(); i++)
        {
            if (people.charAt(i)=='1')
                adult++;
            else
                pupil++;
        }
        new AmusementPark(adult, pupil, c1, c2);
    }
    public static void main(String[] args)
    {
        autoTest();
    }
}
