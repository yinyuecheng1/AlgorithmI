import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStatsQF 
{
	
    private int Tri; // experiment times.
	private double[] count; // the percolation threshold.
    public PercolationStatsQF(int n, int trials) // perform trials independent experiments on an n-by-n grid
    {
    	if(n<=0||trials<=0) throw new java.lang.IllegalArgumentException();
    	Tri=trials;
    	count=new double[trials];
    	for(int i=0;i<trials;i++)
    	   {
    		PercolationQF perco=new PercolationQF(n);
    		while(!perco.percolates())
    	    {
    	    int a=StdRandom.uniform(1,n+1);
    	    int b=StdRandom.uniform(1,n+1);
    	    if(perco.isOpen(a, b)) continue;
    	    else
    	    perco.open(a,b);
    	    }
    		int temp=perco.numberOfOpenSites();
    		count[i]=(double)temp/(n*n);
    		
    		
    		
    	   }
    	
    }
    public double mean()  // sample mean of percolation threshold
    {
    	   	
    	return StdStats.mean(count);
    }
    public double stddev()  // sample standard deviation of percolation threshold
    {
    	return StdStats.stddev(count);
    }
    public double confidenceLo()  // low  endpoint of 95% confidence interval
    {
    	double lo=mean()-1.96*stddev()/Math.sqrt(Tri);
    	return lo;
    }
    public double confidenceHi()   // high endpoint of 95% confidence interval
    {
    	double hi=mean()+1.96*stddev()/Math.sqrt(Tri);
    	return hi;
    }

    public static void main(String[] args)  // test client (described below)
    {
    	//int num=Integer.parseInt(args[0]);
    	//int tim=Integer.parseInt(args[1]);
    	int num=20;
    	int tim=10;
    	PercolationStatsQF stats=new PercolationStatsQF(num,tim);
    	StdOut.println("mean:"+stats.mean());
    	StdOut.println("stddev"+":"+stats.stddev());
    	StdOut.println("95% confidence interval:"+"["+stats.confidenceLo()+
    			           ","+stats.confidenceHi()+"]");
    	
    }
}