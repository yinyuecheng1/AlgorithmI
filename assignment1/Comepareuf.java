import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Comepareuf 
{
	public static double time(String alg,int n,int tri)
	{
		Stopwatch timer=new Stopwatch();
		if(alg.equals("WeightedQU"))
			{
			PercolationStatsWQU statsWqu=new PercolationStatsWQU(n,tri);
			StdOut.println("Wqumean:"+statsWqu.mean());
	    	StdOut.println("Wqustddev"+":"+statsWqu.stddev());
	    	StdOut.println("95% confidence interval:"+"["+statsWqu.confidenceLo()+
	    			           ","+statsWqu.confidenceHi()+"]");

			}
		if(alg.equals("QuickFind"))
		{
			PercolationStatsQF statsQf=new PercolationStatsQF(n,tri);
			StdOut.println("mean:"+statsQf.mean());
	    	StdOut.println("stddev"+":"+statsQf.stddev());
	    	StdOut.println("95% confidence interval:"+"["+statsQf.confidenceLo()+
	    			           ","+statsQf.confidenceHi()+"]");
		}
		return timer.elapsedTime();
	}
	
public static void main(String[] args)
 {
	String alg1=args[0]; // WeightedQuickUnionFind.
	String alg2=args[1]; // QuickFind.
	int n=Integer.parseInt(args[2]);
	int tri=Integer.parseInt(args[3]);
	double t1=time(alg1,n,tri);
	double t2=time(alg2,n,tri);
	double ratio=t1/t2;
	StdOut.printf("%d trials for %d by %d grids use %s algorithm:\n", tri,n,n,alg1);
	StdOut.println("time cost:"+t1);
	StdOut.printf("%d trials for %d by %d grids use %s algorithm:\n", tri,n,n,alg2);
	StdOut.println("time cost:"+t2);
	StdOut.printf("WeightedQuickUnionFind is %.1f times faster than QuickFind",ratio);
	
 }
}
